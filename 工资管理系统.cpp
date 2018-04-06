#include <iostream>
#include <cstdio>
#include <stdlib.h>
#include <cstring>
#include <malloc.h>
#include <ctime>
#include "windows.h"
using namespace std;
#define LEN sizeof(node)

struct employee
{
	int num;
	char name[10];
	int age;
	int price;
	int saraly;
	struct employee *next;
};
int n;               //¼��Ա������

typedef struct employee node;

node* creat()
{
    node *ptr,*head,*newnode;
    head=(node*)malloc(LEN);
    ptr=head;
    ptr->next=NULL;
    cout<<"������Ҫ¼��Ա������:";
    cin>>n;
    do{
        cout<<"������Ա���ı�ţ����������䣬�������ʣ����ʣ�";
        cin>>ptr->num>>ptr->name>>ptr->age>>ptr->price>>ptr->saraly;
        newnode=(node*)malloc(LEN);
        ptr->next=newnode;
        newnode->next=NULL;
        ptr=ptr->next;
        n--;
    }while(n!=0);
    cout<<"¼��Ա����Ϣ����ɣ�"<<endl;
    return head;
}


node* Insert_(node * &head)
{
    cout<<"������Ҫ����ְ����Ϣ(��ţ����������䣬�������ʣ�����)��";
    node* ptr,*temp,*newnode;
    ptr=head;
    newnode=(node*)malloc(LEN);
    cin>>newnode->num>>newnode->name>>newnode->age>>newnode->price>>newnode->saraly;
    int n=newnode->num;
    if(head==NULL)
    {
        head=newnode;
        newnode->next=NULL;
        return head;
    }

    while(n>ptr->num && ptr->next!=NULL)
    {
        temp=ptr;
        ptr=ptr->next;
    }
    if((ptr==head&&head->next!=NULL) || n<=head->num)
    {
        newnode->next=head;
        return newnode;
    }
    if(n>ptr->num&&ptr->next==NULL)
    {
         ptr->next=newnode;
        newnode->next=NULL;
        return head;
    }
    if(n<=ptr->num)
    {
        temp->next=newnode;
        newnode->next=ptr;
        return   head;
    }
}
void change(node *&head)
{
    node *ptr=head;
    int num,age,price,saraly;
    char name[10],sex[10];
    cout<<"������Ҫ�޸���Ϣ��Ա�����:";
    cin>>num;
    cout<<"��������ĺ����Ϣ:";
    cin>>name>>age>>price>>saraly;
    while(ptr->next!=NULL){
        if(ptr->num==num)
            {strcpy(ptr->name,name);
             ptr->age=age;
             ptr->price;
            ptr->saraly=saraly;
            break;
            }
        ptr=ptr->next;
    }
}

void  del_ptr(node* &head)
{
    int num;
    cout<<"������Ҫɾ����Ա�����:";
	cin>>num;
	node* temp,*ptr;
	ptr = head;
	if (head == NULL)
        cout<<"����Ϊ��!\n";
	else
	{
		ptr = head;
		while( ptr->num!=num  && ptr->next!=NULL )
		{
			temp = ptr;
			ptr = ptr->next;
		}
		if( ptr->num==num )
		{
			if (ptr == head)
			{
				head = head->next;
				free(ptr);
			}
			else
			{
				temp->next = ptr->next;
                printf("��ɾ���� %d ��Ա�� ������%s ���䣺%d �������ʣ�%d н��:%d\n",ptr->num,ptr->name,ptr->age,ptr->price,ptr->saraly);
				free(ptr );
			}
		}
		else
			cout<<"�޴�Ա�����\n";
	}

}
void find_ptr(node* &head)
{
    int findword;
    cout<<"������Ҫ���ҵ�Ա���ı�ţ�";
    cin>>findword;
    node* ptr=head;
	int	find=0;
			while (ptr!=NULL)
			{
				if(ptr->num==findword)
				{
					find++;
					break;
				}
				ptr=ptr->next;
			}
			if(find==0)
                printf("û���ҵ���Ա�������Ϣ\n");
			else
                {cout<<"���Ա����Ϣ:"<<endl;
            printf("\t[%2d]\t[ %-10s]\t[%2d][%3d]\t[%3d]\n",ptr->num,ptr->name,ptr->age,ptr->price,ptr->saraly);
        }
}
void print(node* &head)
{
    node* ptr=head;
    if(head==NULL){cout<<"ͷָ��Ϊ�գ�"<<endl;return;}
    printf("��ӡȫ��Ա����Ϣ��");
    printf("\n\n\tԱ�����    ����\t����\t��������\t����\n");          /*��ӡʣ����������*/
	printf("\t====================================================\n");
	while(ptr!=NULL)
	{printf("\t[%2d]\t[ %-10s]\t[%2d]\t[%3d]\t\t[%3d]\n",ptr->num,ptr->name,ptr->age,ptr->price,ptr->saraly);
		ptr=ptr->next;
	}
	printf("\t====================================================\n");
}
void time(node* &head,int Year)
{
    time_t rawtime;
    time ( &rawtime );
    cout<<"��ǰʱ��: "<<ctime(&rawtime)<<endl;
    node*ptr=head;
   int year;
   SYSTEMTIME ct;
   GetLocalTime(&ct);//�����GetSystemTime(&ct);��ô��ȡ���Ǹ������α�׼ʱ��
   year=ct.wYear;
   int n=year-Year;
    if(n){
        while(ptr!=NULL){ptr->age=ptr->age+n;
        ptr=ptr->next;
    }
    }
    if(n!=2)return;
    cout<<"�Ѿ����������Ĺ���Ϊ��"<<endl;

    while(ptr!=NULL)
    {
        ptr->price=ptr->price+30;
        ptr->saraly=ptr->saraly+30;
        printf("\t[%2d]\t[ %-10s]\t[%2d]\t[%3d]\t\t[%3d]\n",ptr->num,ptr->name,ptr->age,ptr->price,ptr->saraly);
        ptr=ptr->next;
    }
    print(head);
}
void destroynode(node* &head)
{
    node* p=head;
    while(p!=NULL){
        node* ptr=p;
        p=p->next;
        free(ptr);
    }
    cout<<"���������٣�"<<endl;
}
void sort(node*&head) //����������
{
    node *p,*q,*r;
    int temp;
    for(p=head->next;p!=NULL;p=p->next)
    {
        r=p;
        for(q=p->next;q!=NULL;q=q->next)
            if(q->num<r->num) r=q;
                temp=p->num;
                p->num=r->num;
                r->num=temp;
    }
}
int main()
{
    node *head;
    head=creat();
    sort(head);
    print(head);
}
