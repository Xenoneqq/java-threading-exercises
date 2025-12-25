import networkx as nx
import matplotlib.pyplot as plt
from matplotlib import cm
import argparse
import sys

def remove_transitive_edges(G: nx.DiGraph()):
    G_reduced = nx.DiGraph()
    G_reduced.add_nodes_from(G.nodes(data=True))
    topo = list(nx.topological_sort(G))
    reachable = {u: set(nx.descendants(G, u)) for u in G.nodes()}

    for u in topo:
        for v in G.successors(u):
            is_transitive = any(
                (w != v) and (v in reachable[w])
                for w in G.successors(u)
            )
            if not is_transitive:
                G_reduced.add_edge(u, v)
    return G_reduced

def diekert_gauss_graph(N: int):
    G = nx.DiGraph()
    for i in range(1, N):
        for k in range(i + 1, N + 1):
            G.add_node(f"A({i},{k})", layer=3*i-2, row=i)
            for j in range(i, N + 2):
                G.add_node(f"B({i},{j},{k})", layer=3*i-1, row=i)
                G.add_node(f"C({i},{j},{k})", layer=3*i,   row=i)

    for i in range(1, N):
        for k in range(i + 1, N + 1):
            for j in range(i, N + 2):
                G.add_edge(f"A({i},{k})", f"B({i},{j},{k})")
                G.add_edge(f"B({i},{j},{k})", f"C({i},{j},{k})")

    for i in range(2, N):
        for k in range(i + 1, N + 1):
            src1 = f"C({i-1},{i},{i})"
            src2 = f"C({i-1},{i},{k})"
            dst  = f"A({i},{k})"
            if src1 in G: G.add_edge(src1, dst)
            if src2 in G: G.add_edge(src2, dst)

        for j in range(i, N + 2):
            for k in range(i + 1, N + 1):
                src  = f"C({i-1},{j},{k})"
                dst1 = f"C({i},{j},{k})"
                dst2 = f"B({i},{j},{k})"
                if src in G:
                    G.add_edge(src, dst1)
                src_B = f"C({i-1},{j},{i})"
                if src_B in G:
                    G.add_edge(src_B, dst2)
    return G

def get_color(node, row):
    base = cm.tab10((row - 1) % 10)
    if node.startswith("A"):
        alpha = 0.55
    elif node.startswith("B"):
        alpha = 0.85
    else:
        alpha = 1.0
    r, g, b, _ = base
    return (r, g, b, alpha)

def draw_diekert(G, n_val):
    layers = {}
    for node, data in G.nodes(data=True):
        layers.setdefault(data["layer"], []).append(node)

    pos = {}
    for layer, nodes in layers.items():
        for idx, node in enumerate(nodes):
            pos[node] = (idx, -layer)

    colors = [get_color(node, G.nodes[node]["row"]) for node in G.nodes()]

    plt.figure(figsize=(20, 12))
    nx.draw(
        G, pos,
        with_labels=True,
        arrows=True,
        node_size=800,
        font_size=6,
        arrowstyle='-|>',
        arrowsize=12,
        node_color=colors
    )
    plt.title(f"Graf Diekerta (N={n_val}) z kolorowaniem warstw Foaty")
    plt.show()

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Wizualizacja grafu Diekerta dla eliminacji Gaussa.")

    parser.add_argument(
        "N", 
        type=int, 
        nargs="?", 
        default=4, 
        help="Rozmiar macierzy (domyślnie: 4, maksymalnie: 8)"
    )

    args = parser.parse_args()

    if args.N > 8:
        print(f"Błąd: Wartość N={args.N} jest zbyt duża. Ze względu na czytelność grafu, maksymalne N to 8.")
        sys.exit(1)
    
    if args.N < 2:
        print("Błąd: N musi być większe lub równe 2.")
        sys.exit(1)

    print(f"Generowanie i optymalizacja grafu dla N = {args.N}...")
    
    try:
        G = diekert_gauss_graph(args.N)
        G = remove_transitive_edges(G)
        draw_diekert(G, args.N)
    except Exception as e:
        print(f"Wystąpił nieoczekiwany błąd: {e}")