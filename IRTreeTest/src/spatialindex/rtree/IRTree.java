package spatialindex.rtree;

import invertedindex.InvertedIndex;
import javafx.scene.layout.Region;
import jdbm.btree.BTree;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import spatialindex.rtree.SQpoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Vector;
import java.util.zip.GZIPInputStream;

import javax.management.Query;

import com.sun.org.apache.bcel.internal.generic.NEW;

import neustore.base.LRUBuffer;

import spatialindex.rtree.RTree.NNComparator;
import spatialindex.spatialindex.IData;
import spatialindex.spatialindex.IEntry;
import spatialindex.spatialindex.Point;
import spatialindex.storagemanager.DiskStorageManager;
import spatialindex.storagemanager.IBuffer;
import spatialindex.storagemanager.IStorageManager;
import spatialindex.storagemanager.PropertySet;
import spatialindex.storagemanager.TreeLRUBuffer;

public class IRTree extends RTree {
	protected InvertedIndex iindex;
	protected int count;

	public IRTree(PropertySet ps, IStorageManager sm, boolean isCreate) throws IOException {
		super(ps, sm);
		int buffersize = (Integer) ps.getProperty("BufferSize");
		int pagesize = (Integer) ps.getProperty("PageSize");
		String file = (String) ps.getProperty("FileName");
		LRUBuffer buffer = new LRUBuffer(buffersize, pagesize);
		iindex = new InvertedIndex(buffer, file + ".iindex", isCreate, pagesize, buffersize);
	}

	// �����������ļ�����( �ڵ�һ�δ���IR����ʱ����д���һ�� )
	public void buildInvertedIndex(BtreeStore docstore) throws Exception {
		count = 0;

		Node n = readNode(m_rootID);
		post_traversal_iindex(n, docstore);
	}

	// ���������������һ������( ������ĺ���buildInvertedIndex���� )
	private Vector post_traversal_iindex(Node n, BtreeStore docstore) throws Exception {
		// ���n��Ҷ��
		if (n.isLeaf()) {
			Hashtable invertedindex = new Hashtable();
			iindex.create(n.m_identifier);// ����һ��B��������
			int child;
			for (child = 0; child < n.m_children; child++) {
				int docID = n.m_pIdentifier[child];
				Vector document = docstore.getDoc(docID);
				if (document == null) {
					System.out.println("Can't find document " + docID);
					System.exit(-1);
				}
				// �����еĵ�����Ŀ����
				iindex.insertDocument(docID, document, invertedindex);
			}
			return iindex.store(n.m_identifier, invertedindex);// ���浽��������
		}
		// �������Ҷ��
		else {
			Hashtable invertedindex = new Hashtable();
			iindex.create(n.m_identifier);
			System.out.println("processing index node " + n.m_identifier);
			System.out.println("level " + n.m_level);
			int child;
			for (child = 0; child < n.m_children; child++) {
				Node nn = readNode(n.m_pIdentifier[child]);
				// ����ݹ���н�������
				Vector pseudoDoc = post_traversal_iindex(nn, docstore);
				int docID = n.m_pIdentifier[child];
				iindex.insertDocument(docID, pseudoDoc, invertedindex);
				count++;
				System.out.println(count + "/" + m_stats.getNumberOfNodes());
			}
			return iindex.store(n.m_identifier, invertedindex);
		}
	}

	public void close() throws IOException {
		flush();
		iindex.close();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void lkt(Vector qwords, Point qpoint, int topk, double alpha) throws Exception {
		// ���洴��һ���µ����ȼ����У� ���еĳ�ʼ������Ϊ100 ��
		// ���ȼ��ıȽϹ�������NNEntryComparatorForEncrypt����
		PriorityQueue queue = new PriorityQueue(100, new NNEntryComparator());
		NNComparator nnc = new NNComparator();// �����ѯ���������Ŀ����С����
		double knearest = Double.MAX_VALUE;
		int count = 0;

		Node n = null;
		// m_id = id; m_shape = mbr; m_pData = pData;
		Data nd = new Data(null, null, m_rootID);//// ��װ���ڵ���Ϣ
		Node root = readNode(m_rootID);// ��ø��ڵ�
		System.out.println("cccccccccccccc���ڵ�Ĳ�=" + root.m_level + "  " + root.m_totalDataLength + " " + root.getShape()
				+ "  " + root.m_capacity + " " + root.m_identifier);
		// m_pEntry = e; m_minDist = f; level = l;
		queue.add(new NNEntry(nd, 0.0, root.m_level));// �����ڵ�������

		while (queue.size() != 0) {
			NNEntry first = (NNEntry) queue.poll();// ��õ�һ������Ԫ�ز�����Ӷ�����ɾ����û���򷵻�null��

			if (count >= topk && first.m_minDist >= knearest)
				break;

			if (first.level > 0)// ����Ҷ�ӽڵ�
			{
				IData fd = (IData) first.m_pEntry;// ������entry(һ����֧ )
				n = readNode(fd.getIdentifier());// �������ڵ�n����Ϣ(����һ����֧�ڵ� )
				iindex.load(n.m_identifier);// ����IRTree�иýڵ�n��ID���еĵ����ļ�
				Hashtable trscore = iindex.textRelevancy(qwords);//// �õ��õ����ļ����ı�����Ե�hashtable
				// System.out.println("n.m_children==="+n.m_children);
				for (int cChild = 0; cChild < n.m_children; cChild++)// �Խڵ�n�ĺ��ӽڵ���б���
				{
					Object var = trscore.get(n.m_pIdentifier[cChild]);// �õ����ӽڵ�id�Ķ�Ӧ�����
					//////////////////////////////
					if (var == null) {

						continue;
					}
					float trs = (Float) var;
					//////////////////////////////
					IEntry e = new Data(trs, n.m_pMBR[cChild], n.m_pIdentifier[cChild]);// Ϊ�ڵ�n���ڵľ��Σ��ڵ�n��id�������һ���������
					double dist = nnc.getMinimumDistance(qpoint, e);// �õ�������ε���С����
					double score = alpha * dist + (1 - alpha) * trs;// �õ�����
					NNEntry e2 = new NNEntry(e, score, n.m_level);
					queue.add(e2);
				}
			} else {
				System.out.println(first.m_pEntry.getIdentifier() + ":" + first.m_minDist);
				count++;
				knearest = first.m_minDist;// ��С����
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public double getD(SQpoint pointA, Point pointB) {
		// System.out.println("AID:"+pointA.getId()+";A����"+pointA.getLongitude()+";Aγ��"+pointA.getLatitude()+"BID:"+pointB.getId()+";B����"+pointB.getLongitude()+";Bγ��"+pointB.getLatitude());
		// System.out.println("ŷʽ���룺"+Math.sqrt(Math.pow(pointA.getLongitude()-pointB.getLongitude(),2)+Math.pow(pointA.getLatitude()-pointB.getLatitude(),2)));
		return Math.sqrt(Math.pow(pointA.getLongitude() - pointB.m_pCoords[0], 2)
				+ Math.pow(pointA.getLatitude() - pointB.m_pCoords[1], 2));
	}

	public void skyline(Vector qwords, ArrayList<SQpoint> queryXY, float alpha, int ObjectNumbers) throws Exception {
		// ���洴��һ���µ����ȼ����У� ���еĳ�ʼ������Ϊ100 ��
		PriorityQueue textnum = new PriorityQueue(100, new NNEntryComparator());
		// ���ȼ��ıȽϹ�������NNEntryComparatorForEncrypt����
		PriorityQueue queue = new PriorityQueue(100, new NNEntryComparator());
		NNComparator nnc = new NNComparator();
		double knearest = Double.MAX_VALUE;
		int count = 0;

		Node n = null;
		Node ntext = null;
		// m_id = id; m_shape = mbr; m_pData = pData; // m_rootID��R����id
		Data nd = new Data(null, null, m_rootID);// node��data���ݽṹ
		Node root = readNode(m_rootID);// ��ø��ڵ�
		// System.out.println(root.getChildrenCount()+"Ϊ��������;;�����µ�����ϵ㣩��������"+root.getChildShape(0)+";;��������"+root.getChildShape(1)+";;��������"+root.getChildShape(2));
		// System.out.println("���ڵ�Ĳ�="+root.m_level);

		queue.add(new NNEntry(nd, 0.0, root.m_level)); // ����������һ��entry
		// m_pEntry��id��shape�� = e; m_minDist = f; level = l;
		// level = 4��ʲô������� ע�⣺ֻ���������ݲ���OK�ģ�
		// �����ڽ�����ʱ����Ҫͳ�ƣ���������Ϣ��OK
		// m_pEntry = e; m_minDist = f; level = l;
		// queue.add(new NNEntry(nd, 0.0, 4));(ԭ�����еĴ��룡��)
		Hashtable ptable = new Hashtable();
		Query queryleaf = new Query();

		// System.out.println(ptable.size());
		while (queue.size() != 0) {
			NNEntry first = (NNEntry) queue.poll();// ��õ�һ������Ԫ��
			// �������Ҷ��
			if (first.level > 0) {
				IData fd = (IData) first.m_pEntry;// ������entry( һ����֧ ) // m_pEntry��һ��entry( id�� shape )
				n = readNode(fd.getIdentifier());// �������ڵ����Ϣ( ����һ����֧�ڵ� )
				// System.out.println("fd.getIdentifier()������ڵ�����ID=="+fd.getIdentifier());

				//////// ��ӡ������ĺ���id
//				for (int r = 0; r < n.getChildrenCount(); r++) {
//					System.out.println(
//							"��" + r + "������::::;;���ӵ�����=" + n.getChildShape(r) + ";;;���ӵ�id=" + n.getChildIdentifier(r));
//				}

				ArrayList<NNE> arr = new ArrayList<NNE>();// ��װ�ɶ�������
				ArrayList<NNE> parr = new ArrayList<NNE>();
				for (int cChild = 0; cChild < n.m_children; cChild++) {
					// for(int dim = 0;dim < n.getChildShape(cChild).getDimension();dim++) {
					/////////////////////////////////////////////////////////////
					// ����ýڵ�Ķ��ӽڵ���Ҷ�ӽڵ㣬��ֱ�Ӵ�������Ķ���
					if ((n.getChildShape(cChild).getMBR().m_pHigh[0] == n.getChildShape(cChild).getMBR().m_pLow[0])
							&& (n.getChildShape(cChild).getMBR().m_pHigh[1] == n.getChildShape(cChild)
									.getMBR().m_pLow[1])) {
						// System.out.println("�ò㵽�������Ҷ�ӽڵ�Ĵ������!!!");//break;
						// System.out.println(n.getChildIdentifier(cChild));
						// �����ı�����Թ�ϣ������Ҷ�ӽڵ���ı������
						float pscore = 0;
						float nums[][] = new float[n.getChildrenCount()][2];
						for (int loc = 0; loc < n.getChildrenCount(); loc++) {
							nums[loc][0] = n.getChildIdentifier(loc);
							nums[loc][1] = 0;
							if (ptable.get(n.getChildIdentifier(loc)) == null) {
								nums[loc][1] = 0;
							}
							if (ptable.get(n.getChildIdentifier(loc)) != null) {
								nums[loc][1] = (float) ptable.get(n.getChildIdentifier(loc));
							}
						}

						for (int leaf = 0; leaf < n.getChildrenCount(); leaf++) {
							float leafscore = nums[leaf][1];
							if (leafscore != 0) {
								IEntry pe = new Data(leafscore, n.m_pMBR[leaf], n.m_pIdentifier[leaf]);
								ArrayList<NNEntry> pQ = new ArrayList<>();
								for (int t = 0; t < queryXY.size(); t++) {

									////// ====��ÿ����ѯ��������Ų�====================================================
									double[] f = new double[2];
									f[0] = queryXY.get(t).getLongitude();
									f[1] = queryXY.get(t).getLatitude();
									Point qpoint = new Point(f);

									double pdist = nnc.getMinimumDistance(qpoint, pe);// ��þ���
									// System.out.println("dist=" + pdist);
									// ����֧��Ƚ�
									float ptrs = (float) (pdist / Math.pow(leafscore, 1.0 / qwords.size()));
									NNEntry pe2 = new NNEntry(pe, ptrs, n.m_level);

									pQ.add(t, pe2);
									// queue.add(e2);
								} // for
									// query=============================================================================
									// float trs=nnc.getMinimumDistance(query, e)
								NNE pe3 = new NNE(pQ, leaf);// Ϊÿһ�����ӽڵ�����Ӧ�����в�ѯ�����װ��һ������
								parr.add(pe3);
								// System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
							}
						}
						break;
						// SQpoint tt = new
						// SQpoint((int)n.getIdentifier(cChild),n.getChildShape(cChild).getMBR().m_pLow[0],n.getChildShape(cChild).getMBR().m_pLow[0]);
					} ///// �ýڵ�Ķ��Ӳ���Ҷ�ӽڵ���Ҫ�����ǵķ����������
					else {
						Data ndtext = new Data(null, null, n.getChildIdentifier(cChild));// �����ӷ�װ��node��data���ݽṹ
						Node roottext = readNode(n.getChildIdentifier(cChild));// ��ø��ڵ� ���ı�
						iindex.load(n.getChildIdentifier(cChild));
						// System.out.println("�ڵ�id="+n.getIdentifier()+"�Ķ��Ӹ�������"+n.getChildrenCount()+"&&&&&�����idΪ����");
						// for(int i=0;i<n.m_children;i++)
						// {
						// System.out.print(n.getChildIdentifier(i)+" ");
						// }
						// System.out.println();
						Hashtable trscore = iindex.skytextRelevancy(qwords, roottext.m_children, alpha);// 1\2\8\15
						ptable.putAll(trscore);

						// ����÷�
						float score = 0;// ������ĵ÷�
						int cout = 0;
						for (int calscore = 1; calscore <= ObjectNumbers; calscore++) {
							if (trscore.get(calscore) != null) {
//								System.out.println((float) trscore.get(calscore));
								if ((float) trscore.get(calscore) > score) {
									score = (float) trscore.get(calscore);
								}
							}
						}

						/*
						 * Object var = trscore.get(n.m_pIdentifier[cChild]);// ������û��������ı�����֮�е�
						 * ////////////////////////////// if(var == null){
						 * 
						 * continue; } float trs = (Float)var;//�����
						 */ ////////////////////////////// public Data(double w, Region mbr, int id) { m_id
							////////////////////////////// = id; m_shape = mbr; weight = w; }
						IEntry e = new Data(score, n.m_pMBR[cChild], n.m_pIdentifier[cChild]);
						// �������
						ArrayList<NNEntry> Q = new ArrayList<>();
						for (int t = 0; t < queryXY.size(); t++) {
							////// ====��ÿ����ѯ��������Ų�====================================================
							double[] f = new double[2];
							f[0] = queryXY.get(t).getLongitude();
							f[1] = queryXY.get(t).getLatitude();
							Point qpoint = new Point(f);

							double dist = nnc.getMinimumDistance(qpoint, e);// ��þ���
							// System.out.println("dist=" + dist);
							// ����֧��Ƚ�
							float trs = 0;
							if (score != 0) {
								// trs = (float) (dist / Math.pow(score, 1.0 / qwords.size()));
								trs = (float) (dist / score);
							}

							// double score = trs;//alpha*dist+(1-alpha)*trs;// ����ĵ÷�
							NNEntry e2 = new NNEntry(e, trs, n.m_level);

							Q.add(t, e2);
							// queue.add(e2);
						} // for
							// query=============================================================================
						NNE e3 = new NNE(Q, cChild);// Ϊÿһ�����ӽڵ�����Ӧ�����в�ѯ�����װ��һ������
						arr.add(e3);
					}
				} // for Child

				//////////////////// ��Ҷ�ӽڵ���֧���������
				if (!parr.isEmpty()) {
					ArrayList List = new ArrayList();
					ArrayList List1 = new ArrayList();
					for (int i = 0; i < parr.size(); i++) {
						int delete = 0;
						if (List.size() != 0) {
							for (int t = 0; t < List.size(); t++) {
								if (i == (int) List.get(t)) {
									delete = 1;
									break;
								}
							}
						}
						for (int j = i + 1; j < parr.size(); j++) {
							if (delete == 1) {
								break;
							}
							int delete2 = 0;
							if (List.size() != 0) {
								for (int t2 = 0; t2 < List.size(); t2++) {
									if (j == (int) List.get(t2)) {
										delete2 = 1;
										break;
									}
								}
							}
							if (delete2 == 1) {
								break;
							}
							int c = 0;
							for (int k = 0; k < queryXY.size(); k++) {
//								System.out.println("x--" + parr.get(i).getEntry().get(k).m_minDist);
//								System.out.println("y--" + parr.get(j).getEntry().get(k).m_minDist);
								if ((parr.get(i).getEntry().get(k).m_minDist) <= (parr.get(j).getEntry()
										.get(k).m_minDist)) {
									c++;// System.out.println("c===="+c);
									continue;
								} else {
									break;
								}
							}
							if (c == queryXY.size()) {
								List.add(j);
							}
						}
						// parr_count = parr_count - List.size();
					}

//					for (int lxt = 0; lxt < parr.size(); lxt++) {
//						System.out.println("xx--" + parr.get(lxt).getEntry().get(0).m_minDist);
//						System.out.println("yy--" + parr.get(lxt).getEntry().get(1).m_minDist);
//					}

					for (int tt = 0; tt < List.size(); tt++) {
						parr.remove(((int) List.get(tt) - tt));
						// System.out.println(parr.size());
					}

//					for (int lxt1 = 0; lxt1 < parr.size(); lxt1++) {
//						System.out.println("xx--" + parr.get(lxt1).getEntry().get(0).m_minDist);
//						System.out.println("yy--" + parr.get(lxt1).getEntry().get(1).m_minDist);
//					}

					for (int i = 0; i < parr.size(); i++) {
						int delete = 0;
						if (List1.size() != 0) {
							for (int t = 0; t < List1.size(); t++) {
								if (i == (int) List1.get(t)) {
									delete = 1;
									break;
								}
							}
						}
						for (int j = i + 1; j < parr.size(); j++) {
							if (delete == 1) {
								break;
							}
							int delete2 = 0;
							if (List1.size() != 0) {
								for (int t2 = 0; t2 < List1.size(); t2++) {
									if (j == (int) List1.get(t2)) {
										delete2 = 1;
										break;
									}
									if (i == (int) List1.get(t2)) {
										delete = 1;
										delete2 = 1;
										break;
									}
								}
							}
							if (delete2 == 1) {
								break;
							}
							int c = 0;
							for (int k = 0; k < queryXY.size(); k++) {
//								System.out.println("x11--" + parr.get(i).getEntry().get(k).m_minDist);
//								System.out.println("y22--" + parr.get(j).getEntry().get(k).m_minDist);
								if (parr.get(i).getEntry().get(k).m_minDist >= parr.get(j).getEntry()
										.get(k).m_minDist) {
									c++;
									continue;
								} else {
									break;
								}
							}
							if (c == queryXY.size()) {// System.out.println(c+"and"+queryXY.size());
								List1.add(i);
							} // ɾ�������У��в���
						}
					}
					for (int tt1 = 0; tt1 < List1.size(); tt1++) {
						parr.remove(((int) List1.get(tt1) - tt1));
						// System.out.println(parr.size());
					}
					// System.out.println("n.mchild="+n.m_children);
					for (int i = 0; i < parr.size(); i++) {
						System.out.print("Skylinepoint:");
						System.out.println(parr.get(i).getEntry().get(0).m_pEntry.getIdentifier());
					} // ��֧��ڵ����queue//���в���ȥ��һ����������ֵ
						// System.out.println("queue.size()="+queue.size());
					parr.clear();
				}
				//////////////////// �Ը������������ҳ�֧������������
				if (!arr.isEmpty()) {
					ArrayList PList = new ArrayList();
					ArrayList PList1 = new ArrayList();
					for (int i = 0; i < arr.size(); i++) {
						int delete = 0;
						if (PList.size() != 0) {
							for (int t = 0; t < PList.size(); t++) {
								if (i == (int) PList.get(t)) {
									delete = 1;
									break;
								}
							}
						}
						for (int j = i + 1; j < arr.size(); j++) {
							if (delete == 1) {
								break;
							}
							int delete2 = 0;
							if (PList.size() != 0) {
								for (int t2 = 0; t2 < PList.size(); t2++) {
									if (j == (int) PList.get(t2)) {
										delete2 = 1;
										break;
									}
								}
							}
							if (delete2 == 1) {
								break;
							}
							int c = 0;
							for (int k = 0; k < queryXY.size(); k++) {
//								System.out.println("x--" + arr.get(i).getEntry().get(k).m_minDist);
//								System.out.println("y--" + arr.get(j).getEntry().get(k).m_minDist);
								if ((arr.get(i).getEntry().get(k).m_minDist) <= (arr.get(j).getEntry()
										.get(k).m_minDist)) {
									c++;// System.out.println("c===="+c);
									continue;
								} else {
									break;
								}
							}
							if (c == queryXY.size()) {
								PList.add(j);
							}
						}
						// arr_count = arr_count - PList.size();
					}

//					for (int lxt = 0; lxt < arr.size(); lxt++) {
//						System.out.println("xx--" + arr.get(lxt).getEntry().get(0).m_minDist);
//						System.out.println("yy--" + arr.get(lxt).getEntry().get(1).m_minDist);
//					}

					for (int tt = 0; tt < PList.size(); tt++) {
						arr.remove(((int) PList.get(tt) - tt));
						// System.out.println(arr.size());
					}

//					for (int lxt1 = 0; lxt1 < arr.size(); lxt1++) {
//						System.out.println("xx--" + arr.get(lxt1).getEntry().get(0).m_minDist);
//						System.out.println("yy--" + arr.get(lxt1).getEntry().get(1).m_minDist);
//					}

					for (int i = 0; i < arr.size(); i++) {
						int delete = 0;
						if (PList1.size() != 0) {
							for (int t = 0; t < PList1.size(); t++) {
								if (i == (int) PList1.get(t)) {
									delete = 1;
									break;
								}
							}
						}
						for (int j = i + 1; j < arr.size(); j++) {
							if (delete == 1) {
								break;
							}
							int delete2 = 0;
							if (PList1.size() != 0) {
								for (int t2 = 0; t2 < PList1.size(); t2++) {
									if (j == (int) PList1.get(t2)) {
										delete2 = 1;
										break;
									}
									if (i == (int) PList1.get(t2)) {
										delete = 1;
										delete2 = 1;
										break;
									}
								}
							}
							if (delete2 == 1) {
								break;
							}
							int c = 0;
							for (int k = 0; k < queryXY.size(); k++) {
//								System.out.println("x11--" + arr.get(i).getEntry().get(k).m_minDist);
//								System.out.println("y22--" + arr.get(j).getEntry().get(k).m_minDist);
								if (arr.get(i).getEntry().get(k).m_minDist >= arr.get(j).getEntry().get(k).m_minDist) {
									c++;
									continue;
								} else {
									break;
								}
							}
							if (c == queryXY.size()) {// System.out.println(c+"and"+queryXY.size());
								PList1.add(i);
							} // ɾ�������У��в���
						}
					}
					for (int tt1 = 0; tt1 < PList1.size(); tt1++) {
						arr.remove(((int) PList1.get(tt1) - tt1));
						// System.out.println(arr.size());
					}
					// System.out.println("n.mchild="+n.m_children);
//					for (int i = 0; i < arr.size(); i++) {
//						System.out.print("Skylinepoint:");
//						System.out.println(arr.get(i).getEntry().get(0).m_pEntry.getIdentifier());
//					} // ��֧��ڵ����queue//���в���ȥ��һ����������ֵ
//						// System.out.println("queue.size()="+queue.size());
//					arr.clear();
				}
				// if (!arr.isEmpty()) {
				// for (int i = 0; i < arr.size(); i++) {
				// for (int j = i + 1; j < arr.size(); j++) {
				// int c = 0;
				// for (int k = 0; k < queryXY.size(); k++) {
				//// System.out.println("x--"+arr.get(i).getEntry().get(k).m_minDist);
				//// System.out.println("y--"+arr.get(j).getEntry().get(k).m_minDist);
				// if ((arr.get(i).getEntry().get(k).m_minDist) <= (arr.get(j).getEntry()
				// .get(k).m_minDist)) {
				// c++;// System.out.println("c===="+c);
				// continue;
				// } else {
				// break;
				// }
				// }
				// // System.out.println(c+"orand"+queryXY.size());
				// if (c == queryXY.size()) {
				// // System.out.println(c+"==and"+queryXY.size());
				// arr.remove(j);
				// }
				// }
				// }
				// // System.out.println("n.mchild="+n.m_children);
				// for (int i = 0; i < arr.size(); i++) {
				// for (int j = i + 1; j < arr.size(); j++) {
				// int c = 0;
				// for (int k = 0; k < queryXY.size(); k++) {
				//// System.out.println("x11--"+arr.get(i).getEntry().get(k).m_minDist);
				//// System.out.println("y22--"+arr.get(j).getEntry().get(k).m_minDist);
				// if (arr.get(i).getEntry().get(k).m_minDist >=
				// arr.get(j).getEntry().get(k).m_minDist) {
				// c++;
				// continue;
				// } else {
				// break;
				// }
				// }
				// if (c == queryXY.size()) {// System.out.println(c+"and"+queryXY.size());
				// arr.remove(i);
				// } // ɾ�������У��в���
				// }
				// }

				for (int i = 0; i < arr.size(); i++) {
					queue.add(arr.get(i).getEntry().get(0));
					//System.out.println("֧������=" + arr.get(i).getEntry().get(0).m_pEntry.getIdentifier());
				} // ��֧��ڵ����queue//���в���ȥ��һ����������ֵ
					// System.out.println("queue.size()="+queue.size());
				arr.clear();
			} else// Ҷ�ӽڵ�
			{
				System.out.println("Skylinepoint:");
				System.out.println(first.m_pEntry.getIdentifier() + ":" + first.m_minDist);
				// count++;
				// knearest = first.m_minDist;
			}

		}
		// }//=======================================================================
	}

	public long getIO() {
		return m_stats.getReads() + iindex.buffer.getIOs()[0];
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 4) {
			System.err.println("Usage: IRTree docstore tree_file fanout buffersize.");
			System.exit(-1);
		}
		String docfile = args[0];
		String treefile = args[1];
		int fanout = Integer.parseInt(args[2]);
		int buffersize = Integer.parseInt(args[3]);

		BtreeStore docstore = new BtreeStore(docfile, false);

		// Create a disk based storage manager.
		PropertySet ps = new PropertySet();

		ps.setProperty("FileName", treefile);
		// .idx and .dat extensions will be added.

		Integer i = new Integer(4096 * fanout / 100);
		ps.setProperty("PageSize", i);
		// specify the page size. Since the index may also contain user defined data
		// there is no way to know how big a single node may become. The storage manager
		// will use multiple pages per node if needed. Off course this will slow down
		// performance.

		ps.setProperty("BufferSize", buffersize);

		IStorageManager diskfile = new DiskStorageManager(ps);

		IBuffer file = new TreeLRUBuffer(diskfile, buffersize, false);
		// applies a main memory random buffer on top of the persistent storage manager
		// (LRU buffer, etc can be created the same way).

		i = new Integer(1); // INDEX_IDENTIFIER_GOES_HERE (suppose I know that in this case it is equal to
							// 1);
		ps.setProperty("IndexIdentifier", i);

		IRTree irtree = new IRTree(ps, file, true);

		long start = System.currentTimeMillis();
		// ע���ڹ���IR����ͬʱ��Ҫ��������������
		// ���Ĺ�����ȫ�Ǹ���֮ǰ��R���Ľڵ�������ģ���Ϊÿһ��R���Ľڵ㶼����һ���Լ��ĵ������ļ�
		irtree.buildInvertedIndex(docstore);

		long end = System.currentTimeMillis();
		boolean ret = irtree.isIndexValid();
		if (ret == false)
			System.err.println("Structure is INVALID!");
		irtree.close();

		System.out.println("Minutes: " + ((end - start) / 1000.0f) / 60.0f);
		System.out.println("Finish!!");

	}
}
