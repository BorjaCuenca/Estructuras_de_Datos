
package org.uma.ed.datastructures.graph;

import java.util.StringJoiner;
import org.uma.ed.datastructures.dictionary.Dictionary;
import org.uma.ed.datastructures.dictionary.JDKHashDictionary;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.set.Set;

/**
 * Class for directed graphs implemented with a dictionary.
 *
 * @param <V> Type for vertices in graph
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class DictionaryDiGraph<V> implements DiGraph<V> {
  private final Set<V> vertices;               // set with all vertices in graph
  private final Dictionary<V, Set<V>> diEdges; // dictionary with sources as keys and Set of destinations as values

  public DictionaryDiGraph() {
    vertices = JDKHashSet.empty();
    diEdges = JDKHashDictionary.empty();
  }

  /**
   * Creates an empty directed graph.
   *
   * @param <V> Type for vertices in graph.
   *
   * @return An empty directed graph.
   */
  public static <V> DictionaryDiGraph<V> empty() {
    return new DictionaryDiGraph<>();
  }

  /**
   * Creates a directed graph with given vertices and edges.
   *
   * @param vertices vertices to add to graph.
   * @param edges edges to add to graph.
   * @param <V> Type for vertices in graph.
   *
   * @return A DictionaryDiGraph with given vertices and edges.
   */
  public static <V> DictionaryDiGraph<V> of(Set<V> vertices, Set<DiEdge<V>> edges) {
    DictionaryDiGraph<V> diGraph = new DictionaryDiGraph<>();
    for (V vertex : vertices) {
      diGraph.addVertex(vertex);
    }
    for (DiEdge<V> edge : edges) {
      diGraph.addDiEdge(edge.source(), edge.destination());
    }
    return diGraph;
  }

  /**
   * Creates a directed graph with same vertices and edges as given graph.
   *
   * @param diGraph Graph to copy.
   * @param <V> Type for vertices in graph.
   *
   * @return A DictionaryDiGraph with same vertices and edges as given graph.
   */
  public static <V> DictionaryDiGraph<V> copyOf(DiGraph<V> diGraph) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return vertices.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addVertex(V v) {
    vertices.insert(v);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addDiEdge(V source, V destination) {
    Set<V> destinations = diEdges.valueOfOrDefault(source, new JDKHashSet<>());
    destinations.insert(destination);
    diEdges.insert(source, destinations);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteDiEdge(V source, V destination) {
    Set<V> destinations = diEdges.valueOfOrDefault(source, new JDKHashSet<>());
    destinations.delete(destination);
    if (destinations.isEmpty()){
      diEdges.delete(source);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteVertex(V vertex) {
    for (V vertice : vertices){
      deleteDiEdge(vertice, vertex);
    }
    diEdges.delete(vertex);
    vertices.delete(vertex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<V> vertices() {
    return vertices;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<DiEdge<V>> edges() {
    Set<DiEdge<V>> edges = new JDKHashSet<>();
    for (V source : vertices){
      for (V destination : diEdges.valueOfOrDefault(source, new JDKHashSet<>())){
        edges.insert(new DiEdge<>(source, destination));
      }
    }
    return edges;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int numberOfVertices() {
    return vertices.size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int numberOfEdges() {
    return edges().size();
  }

  /**
   * Returns the successors of a vertex in graph (i.e. vertices to which there is an edge from given vertex).
   *
   * @param source vertex for which we want to obtain its successors.
   *
   * @return Successors of a vertex.
   */
  @Override
  public Set<V> successors(V source) {
    return diEdges.valueOfOrDefault(source, new JDKHashSet<>());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<V> predecessors(V destination) {
    Set<V> predecessors = new JDKHashSet<>();
    for (Dictionary.Entry<V, Set<V>> entry : diEdges.entries()) {
      if (entry.value().contains(destination)) {
        predecessors.insert(entry.key());
      }
    }
    return predecessors;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int inDegree(V vertex) {
    return predecessors(vertex).size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int outDegree(V vertex) {
    return successors(vertex).size();
  }

  @Override
  public String toString() {
    String className = getClass().getSimpleName();

    StringJoiner verticesSJ = new StringJoiner(", ", "vertices(", ")");
    for (V vertex : vertices()) {
      verticesSJ.add(vertex.toString());
    }

    StringJoiner edgesSJ = new StringJoiner(", ", "edges(", ")");
    for (DiEdge<V> edge : edges()) {
      edgesSJ.add(edge.toString());
    }

    StringJoiner sj = new StringJoiner(", ", className + "(", ")");
    sj.add(verticesSJ.toString());
    sj.add(edgesSJ.toString());
    return sj.toString();
  }
}
