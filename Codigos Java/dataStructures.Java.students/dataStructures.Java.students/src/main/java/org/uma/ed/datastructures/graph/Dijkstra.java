
package org.uma.ed.datastructures.graph;

import org.uma.ed.datastructures.dictionary.Dictionary;
import org.uma.ed.datastructures.dictionary.JDKHashDictionary;
import org.uma.ed.datastructures.list.JDKArrayList;
import org.uma.ed.datastructures.list.List;
import org.uma.ed.datastructures.priorityqueue.JDKPriorityQueue;
import org.uma.ed.datastructures.priorityqueue.PriorityQueue;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.set.Set;
import org.uma.ed.datastructures.tuple.Tuple2;

/**
 * Class for computing shortest paths in a weighted graph using Dijkstra's algorithm.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class Dijkstra {
   /**
   * Computes costs of shortest paths from a source vertex to all other vertices in a weighted graph.
   *
   * @param weightedGraph The weighted graph.
   * @param source The source vertex.
   * @param <V> The type of the vertices in the graph.
   *
   * @return a dictionary where keys are vertices and values are the minimum cost to reach them from the source.
   */
  public static <V> Dictionary<V, Integer> dijkstra(
      WeightedGraph<V, Integer> weightedGraph, V source) {
 
    // Class for representing an extension of a path from vertex source to
    // vertex destination and total cost of reaching destination.
    // This class implements Comparable interface to allow sorting of extensions based on total cost.
    record Extension<V>(V source, V destination, Integer totalCost) implements Comparable<Extension<V>> {
      @Override
      // Best extension is the one with the smallest total cost.
      // Will be used later by the priority queue.
      public int compareTo(Extension that) {
        return this.totalCost.compareTo(that.totalCost);
      }

      static <V> Extension<V> of(V source, V destination, Integer totalCost) {
        return new Extension<>(source, destination, totalCost);
      }
    }

    Set <V> verticesOpt = new JDKHashSet<>();
    verticesOpt.insert(source);
    Set <V> vertices = weightedGraph.vertices();
    vertices.delete(source);

    Dictionary<V, Integer> costOpt = new JDKHashDictionary<>();
    costOpt.insert(source, 0);

    PriorityQueue<Extension<V>> queue = new JDKPriorityQueue<Extension<V>>(Extension::compareTo);

    while (!vertices.isEmpty()) {
      for (V vertexOpt : verticesOpt) {
        for (WeightedGraph.Successor<V, Integer> vertex : weightedGraph.successors(vertexOpt)) {
          if (vertices.contains(vertex.vertex())) {
            queue.enqueue(Extension.of(vertexOpt, vertex.vertex(),
                    costOpt.valueOf(vertexOpt) + vertex.weight()));
          }
        }
      }
      Extension<V> minExtension = queue.first();
      queue.clear();
      costOpt.insert(minExtension.destination(), minExtension.totalCost());
      verticesOpt.insert(minExtension.destination());
      vertices.delete(minExtension.destination());
    }

    return costOpt;
  }

  /**
   * Computes shortest paths (and their costs) from a source vertex to all other vertices in a weighted graph.
   *
   * @param weightedGraph The weighted graph.
   * @param source The source vertex.
   * @param <V> The type of the vertices in the graph.
   *
   * @return a dictionary where keys are vertices and values are pairs with the minimum cost to reach them from the
   * source and the path to reach them.
   */
  public static <V> Dictionary<V, Tuple2<Integer, List<V>>> dijkstraPaths(
      WeightedGraph<V, Integer> weightedGraph, V source) {
      
    // Class for representing an extension of a path from vertex source to
    // vertex destination and total cost of reaching destination plus path from source to destination.
    // This class implements Comparable interface to allow sorting of extensions based on total cost.
    record Extension<V>(V source, V destination, Integer totalCost, List<V> path) implements Comparable<Extension<V>> {
      @Override
      // Best extension is the one with the smallest total cost.
      // Will be used later by the priority queue.
      public int compareTo(Extension that) {
        return this.totalCost.compareTo(that.totalCost);
      }

      static <V> Extension<V> of(V source, V destination, Integer totalCost, List<V> path) {
        return new Extension<>(source, destination, totalCost, path);
      }
    }

    Set <V> verticesOpt = new JDKHashSet<>();
    verticesOpt.insert(source);
    Set <V> vertices = weightedGraph.vertices();
    vertices.delete(source);

    Dictionary<V, Tuple2<Integer, List<V>>> costOpt = new JDKHashDictionary<>();
    costOpt.insert(source, Tuple2.of(0, JDKArrayList.of(source)));

    PriorityQueue<Extension<V>> queue = new JDKPriorityQueue<Extension<V>>(Extension::compareTo);

    while (!vertices.isEmpty()) {
      for (V vertexOpt : verticesOpt) {
        for (WeightedGraph.Successor<V, Integer> vertex : weightedGraph.successors(vertexOpt)) {
          if (vertices.contains(vertex.vertex())) {
            JDKArrayList<V> newPath = JDKArrayList.copyOf(costOpt.valueOf(vertexOpt)._2());
            newPath.append(vertex.vertex());

            queue.enqueue(Extension.of(vertexOpt, vertex.vertex(),
                    costOpt.valueOf(vertexOpt)._1() + vertex.weight(), newPath));
          }
        }
      }
      Extension<V> minExtension = queue.first();
      queue.clear();

      costOpt.insert(minExtension.destination(), Tuple2.of(minExtension.totalCost(), minExtension.path()));
      verticesOpt.insert(minExtension.destination());
      vertices.delete(minExtension.destination());
    }

    return costOpt;
  }
}
