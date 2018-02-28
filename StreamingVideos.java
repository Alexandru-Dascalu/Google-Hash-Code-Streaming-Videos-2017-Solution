import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class StreamingVideos
{
	public static void main(String[] args) throws FileNotFoundException
	{
		/*Change the value of the file that kb is tied to use other input files.*/
		File fin1=new File("me_at_the_zoo.in");
		File fin2=new File("videos_worth_spreading.in");
		File fin3=new File("me_at_the_zoo.in");
		File fin4=new File("kittens.in");
		Scanner reader= new Scanner(fin1);
		
		File fout=new File("result.out");
		PrintWriter writer=new PrintWriter(fout);
		
		
		ArrayList<Cache> caches=new ArrayList<>();
		ArrayList<Video> videos=new ArrayList<>();
		ArrayList<Endpoint> endPoints=new ArrayList<>();
		ArrayList<RequestDescription> requestDescriptions=new ArrayList<>();
		
		int nrVideos=reader.nextInt();
		int nrEndpoints=reader.nextInt();
		int nrRequests=reader.nextInt();
		int nrCaches=reader.nextInt();
		int cacheSize=reader.nextInt();
		
		for(int i=0;i<nrCaches;i++)
		{
			caches.add(new Cache(i,cacheSize));
		}
		
		for(int i=0;i<nrVideos;i++)
		{
			int videoSize=reader.nextInt();
			videos.add(new Video(i,videoSize));
		}
		
		for(int i=0;i<nrEndpoints;i++)
		{
			int latency=reader.nextInt();
			endPoints.add(new Endpoint(i, latency));
			int cacheConnections=reader.nextInt();
			for(int j=0;j<cacheConnections;j++)
			{
				int index=reader.nextInt();
				int cacheLatency=reader.nextInt();
				
				endPoints.get(i).getConnections().add(new CacheConnection(caches.get(index), cacheLatency));
			}
		}
		
		for(int i=0;i<nrRequests;i++)
		{
			int videoIndex=reader.nextInt();
			int endPointName=reader.nextInt();
			int requests=reader.nextInt();
			
			if(videos.get(videoIndex).getSize()<=caches.get(0).getMaxCapacity())
			{
				requestDescriptions.add(new RequestDescription(requests, videos.get(videoIndex),endPoints.get(endPointName)));
			}
		}
		
		for(Endpoint elem: endPoints)
		{
			Collections.sort(elem.getConnections());
			
			//quickSort(elem.getConnections(),0, elem.getConnections().size()-1);
			if(elem.getConnections().size()!=0)
			{
				elem.setBestConnection(elem.getConnections().get(0).getLatency());
			}
			else
			{
				elem.setBestConnection(elem.getDCLatency());
			}
		}
		
		Collections.sort(requestDescriptions);

		/*for(RequestDescription request: requestDescriptions)
		{
			System.out.println(request.getNoRequests()*request.getEndpoint().getBestConnection());
		}*/
		
		int usedCaches=0;
		for(RequestDescription elem: requestDescriptions)
		{
			Video video=elem.getVideo();
			for(CacheConnection connection: elem.getEndpoint().getConnections())
			{
				if(video.getSize()<=connection.getCache().getCapacity() && !connection.getCache().getVideos().contains(video)) 
				{
					if(connection.getCache().getCapacity()==connection.getCache().getMaxCapacity())
					{
						usedCaches++;
					}
					connection.getCache().getVideos().add(video);
					connection.getCache().store(video.getSize());
					break;
				}
			}
		}
		
		writer.println(usedCaches);
		for(Cache elem: caches)
		{
			writer.print(elem.getIndex()+" ");
			for(Video someVideo: elem.getVideos())
			{
				writer.print(someVideo.getIndex()+" ");
			}
			
			writer.print("\n");
		}
		
		reader.close();
		writer.close();
	}
	
	/*public static void quickSort(ArrayList<CacheConnection> connections, int first, int last)
	{
		int repetitions=0;
		if(first<last)
		{
			int splitVal=connections.get(first).getLatency();
			int left=first+1, right=last, splitPoint;
			
			while(left<=right)
			{
				while(connections.get(left).getLatency()<=splitVal && left<=right)
				{
					left++;
					repetitions++;
				}
				
				while(connections.get(right).getLatency()>=splitVal && left<=right)
				{
					right--;
					repetitions++;
				}
				
				if(left<right)
				{
					CacheConnection temp=connections.get(left);
					connections.set(left,connections.get(right));
					connections.set(right,temp);
				}
			}
			
			splitPoint=right;
			CacheConnection temp=connections.get(first);
			connections.set(first,connections.get(splitPoint));
			connections.set(splitPoint, temp);
			
			
			quickSort(connections,first,splitPoint-1);
			quickSort(connections, splitPoint+1, last);
		}
		
	}*/
}
