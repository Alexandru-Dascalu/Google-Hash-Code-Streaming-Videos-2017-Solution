
public class RequestDescription implements Comparable<RequestDescription>
{
	private final int numberOfRequests;
	private final Video video;
	private final Endpoint endpoint;
	
	public RequestDescription(int number, Video video, Endpoint endpoint)
	{
		numberOfRequests=number;
		this.video=video;
		this.endpoint=endpoint;
	}
	
	public int getNoRequests()
	{
		return numberOfRequests;
	}
	
	public Video getVideo()
	{
		return video;
	}
	
	public Endpoint getEndpoint()
	{
		return endpoint;
	}

	@Override
	public int compareTo(RequestDescription arg0)
	{
		return Integer.compare(arg0.getNoRequests()*arg0.getEndpoint().getBestConnection(), 
				this.getNoRequests()*this.getEndpoint().getBestConnection());
	}
}
