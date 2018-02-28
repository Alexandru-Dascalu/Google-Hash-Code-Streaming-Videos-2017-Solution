
public class CacheConnection implements Comparable<CacheConnection>
{
	private final Cache cache;
	private final int latency;
	
	public CacheConnection(Cache cache, int latency)
	{
		this.cache=cache;
		this.latency=latency;
	}
	
	public int getLatency()
	{
		return latency;
	}
	
	public Cache getCache()
	{
		return cache;
	}

	@Override
	public int compareTo(CacheConnection arg0)
	{
		return Integer.compare(this.getLatency(), arg0.getLatency());
	}
	
	
}
