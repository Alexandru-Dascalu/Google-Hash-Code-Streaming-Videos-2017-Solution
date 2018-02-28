import java.util.ArrayList;

public class Endpoint
{
	private final int index;
	private final int datacenterLatency;
	private ArrayList<CacheConnection> connections;
	private int bestConnection;
	
	public Endpoint(int index, int latency)
	{
		this.index=index;
		datacenterLatency=latency;
		connections=new ArrayList<CacheConnection>();
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public int getDCLatency()
	{
		return datacenterLatency;
	}
	
	public ArrayList<CacheConnection> getConnections()
	{
		return connections;
	}
	
	public int getBestConnection()
	{
		return bestConnection;
	}
	
	public void setBestConnection(int latency)
	{
		bestConnection=datacenterLatency-latency;
	}
}
