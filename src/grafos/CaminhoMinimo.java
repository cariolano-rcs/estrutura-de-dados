package grafos;

import java.util.Scanner;

public class CaminhoMinimo {

	public static class vertice
	{
		int num;
		int peso;
		vertice prox;
	}
	
	public static class listaadj
	{
		public vertice listav;
	}
	static ListaPrior lista; //lista de prioridade
	static Scanner entrada= new Scanner(System.in);
	static int marcado[];//vetor para marcar
	static int pai[]; //antecessor dos vertices no caminho minimo
	static int dist[];//distancia minima em relacao a origem
	static listaadj Adj[];//lista de adjacencias dos vertices
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		vertice novo;
		
		int tam, //numero de vertices do grafo.
		org, //vertice de origem da aresta.
		dest, //vertice destino na aresta.
		op, //opcao do menu.
		num=0, //vertice de origem do caminho minimo.
		flag=0, //variavel para validacao do menu
		peso=0; //peso da aresta do grafo.

	System.out.println("Digite numero de vertices do grafo orientado: ");
	tam = entrada.nextInt();

	Adj=new listaadj[tam+1];
	marcado=new int[tam+1];
	pai=new int[tam+1];
	dist=new int [tam+1];

	for(int i= 1; i <= tam; i++)
	{
		Adj[i]= new listaadj();
		marcado[i]=0;
	}

	System.out.println("Arestas do grafo: Vertice Origem(-1 para parar): ");
	org= entrada.nextInt();

	System.out.println("Arestas do grafo: Vertice Destino(-1 para parar)");
	dest= entrada.nextInt();
	
	while(org != -1 && dest != -1)
	{
		
		System.out.println("Peso da Aresta: ");
		peso= entrada.nextInt();
		/*
		alocando um no com o valor do vertice destino para
		colocar na entrada do vertice de origem da lista
		de adjacencias --> (u,v).
		*/
		novo= new vertice();
		novo.num= dest;
		novo.peso= peso;
		/*inserindo vertice adjacente a vertice org
		na lista de adjacencias*/
		novo.prox= Adj[org].listav;
		Adj[org].listav=novo;
		
		//proxima entrada
		System.out.println("Aresta do grafo: Vertice origem(-1 para parar)");
		org= entrada.nextInt();
		System.out.println("\n Aresta do grafo: Vertice destino(-1 para parar)");
		dest= entrada.nextInt();
		
		
	}//fim do while
	do
	{
		System.out.println("1-Aplicando algoritmo de Kijkstra - Caminho minimo: ");
		System.out.println("2-Monstrar lista de adjacencias: ");
		System.out.println("3-Mostrar distancias: ");
		System.out.println("4-Sair.");
		System.out.println("Digite sua opcao: ");
		op= entrada.nextInt();
		
		if(op==1){
			System.out.println("\nDigite um vertice de origem: ");
			num= entrada.nextInt();
			
			for(int i=1; i<=tam; i++)
			{
				marcado[i]=0;
				dist[i]=0;
			}
			dijkstra(Adj,tam, num);
			flag=1;
			
			
		}
		else if(op==2){
			mostrar_Adj(Adj, tam);
		}
		else if(op==3)
		{
			if(flag==0)
				System.out.println("Eh necessario realizar a busca primeiro.");
			else mostrar_dist(tam,num);	
		}
		
		
		
	}while(op!=4);


}//fim main
	
static void dijkstra(listaadj Adj[], int tam, int v)
{	
	int i,w;
	int C[] = new int[tam];
	int tamC = 0;
	lista = new ListaPrior(tam); //lista de prioridades
	
	
	dist[v] = 0;
	lista.inserir(v, dist);
	//imprimir(lista);
	for(i=1; i <= tam; i++)
	{
		if(i != v)
		{
			dist[i]=Integer.MAX_VALUE;
			pai[i]= 0;
			lista.inserir(i, dist);
			
		}
	}

	while(lista.tam != 0)
	{
		w=lista.remover(dist);
		C[tamC]=w;
		tamC++;
		
		vertice x = Adj[w].listav;
		while(x != null)
		{
			//relax (w,x,peso_wx)
			relax(w, x.num, x.peso);
			//proximo vizinho de w
			x=x.prox;
				
		}
		lista.constroiheap(dist);
		
	}
		
}
static void relax(int u, int v, int peso)
{

	if(dist[v] > dist[u]+peso)
	{
	dist[v] = dist[u] + peso;
	pai[v] = u;
	}

}


static void mostrar_Adj(listaadj Adj[],int tam)
{
	vertice v;
	for(int i=1; i <= tam; i++)
	{
		v = Adj[i].listav;
		System.out.println("Entrada"+ i +" ");
		while(v != null)
		{
			System.out.println("("+ i +","+ v.num +")"+" ");
			v=v.prox;
		}
	}
}//fim mostrar_Adj

static void mostrar_dist(int tam,int or)
{
	System.out.println("\nDistancia da origem "+ or +" para os demais vertices");
	for(int i=1; i<=tam; i++)
	{
		System.out.println("\nVertice "+ i +" -> "+ dist[i]);
		
	}
}//fim mostrar_dist

}	//fim main
	
	
