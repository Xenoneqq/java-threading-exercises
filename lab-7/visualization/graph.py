import networkx as nx
import matplotlib.pyplot as plt
from matplotlib import cm


# -----------------------------------------------------
# Usuwanie przechodnich krawędzi
# -----------------------------------------------------
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


# -----------------------------------------------------
# Generator grafu Diekerta
# -----------------------------------------------------
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


# -----------------------------------------------------
# Kolorowanie w normie Foaty:
# row i → kolor bazowy
# A/B/C → różne odcienie
# -----------------------------------------------------
def get_color(node, row):
    base = cm.tab10((row - 1) % 10)  # bazowy kolor dla wiersza i

    if node.startswith("A"):
        alpha = 0.55  # jasny
    elif node.startswith("B"):
        alpha = 0.85  # średni
    else:
        alpha = 1.0   # ciemny (C)

    r, g, b, _ = base
    return (r, g, b, alpha)


# -----------------------------------------------------
# Rysowanie grafu Diekerta z kolorowaniem
# -----------------------------------------------------
def draw_diekert(G):
    layers = {}
    for node, data in G.nodes(data=True):
        layers.setdefault(data["layer"], []).append(node)

    pos = {}
    for layer, nodes in layers.items():
        for idx, node in enumerate(nodes):
            pos[node] = (idx, -layer)

    colors = [
        get_color(node, G.nodes[node]["row"])
        for node in G.nodes()
    ]

    plt.figure(figsize=(22, 16))
    nx.draw(
        G, pos,
        with_labels=True,
        arrows=True,
        node_size=900,
        font_size=7,
        arrowstyle='-|>',
        arrowsize=14,
        node_color=colors
    )

    plt.title("Graf Diekerta z kolorowaniem warstw Foaty")
    plt.show()


# -----------------------------------------------------
# Generowanie i rysowanie N=4
# -----------------------------------------------------
G = diekert_gauss_graph(4)
G = remove_transitive_edges(G)
draw_diekert(G)
