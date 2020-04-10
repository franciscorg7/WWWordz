package puzzle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph<T> { 

	private Map<T, List<T> > map = new HashMap<>(); 

	public void addVertex(T s) { 
		map.put(s, new LinkedList<T>()); 
	} 

	public void addEdge(T source, T destination) 
	{ 
		if (!map.containsKey(source)) 
			addVertex(source); 

		if (!map.containsKey(destination)) 
			addVertex(destination); 

		map.get(source).add(destination); 
	} 
 
	public boolean hasEdge(T s, T d) 
	{ 
		if (map.get(s).contains(d)) return true;
		return false;
	} 

	@Override
	public String toString() 
	{ 
		StringBuilder builder = new StringBuilder(); 

		for (T v : map.keySet()) { 
			builder.append(v.toString() + ": "); 
			for (T w : map.get(v)) { 
				builder.append(w.toString() + " "); 
			} 
			builder.append("\n"); 
		} 

		return (builder.toString()); 
	} 
}  