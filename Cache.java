import java.util.ArrayList;

public class Cache
{
	private int capacity;
	private final int maxCapacity;
	private final int cacheIndex;
	private ArrayList<Video> videos;
	
	public Cache (int index, int size)
	{
		maxCapacity=size;
		capacity=size;
		cacheIndex=index;
		videos=new ArrayList<Video>();
	}
	
	public int getIndex() 
	{
		return cacheIndex;
	}
	
	public int getCapacity()
	{
		return capacity;
	}
	
	public int getMaxCapacity()
	{
		return maxCapacity;
	}
	
	public ArrayList<Video> getVideos()
	{
		return videos;
	}
	
	public void store(int videoSize)
	{
		capacity-=videoSize;
	}
	
}
