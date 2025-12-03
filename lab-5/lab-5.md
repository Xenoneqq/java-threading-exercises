## Zadanie 1

alfabet operacji = {a, b, c, d}

|co robi?|x|y|z|
|-|-|-|-|
| tylko odczytuje   |ac|abd|bcd|
| modyfikuje        |ac|b|d|

Zbiór zależny:
```
sym( (a,c), (a,b), (b,d), (c,d) );
```

Zbiór niezależny:
```
(a,d), (c,d), (b,c), (c,b)
```

Zbiór identyczności:
```
(a,a), (b,b), (c,c), (d,d)
```

W grafie Diekreta gdy chcemy kolorować graf to robimy algorytm:

```python
while (len(wierzchołki) != 0):
    1. Bierzemy zbiór wierzchołków brz wchodzących krawędzi
    2. Wrzucamy je do jednego koloru (klasy)
    3. Usuwamy te wierzchołki i ich krawędzie wychodzące
```

Zatem postać Foaty to:
```
FNF = [b][a,d][a][c,b]
```

Możliwe ciągi operacji:

```
badacb, bdaacb, badabc, bdaabc, baadcb, baadbc
```

---

## Zadanie 2

Oprócz tabelki można porównywać ze sobą operacje po kolei:

### Dane:

```
(a) x <- y + z
(b) y <- x + w + y
(c) x <- x + y + v
(d) w <- v + z
(e) v <- x + v + w
(f) z <- x + z + v
```

### Wyznaczenie Zależności:

```
(a,b): zależne
(a,c): zależne
(a,d): nie zależne
(a,e): zależne
(a,f): zależne
(b,c): zależne
(b,d): zależne
. . .
```

### Finalne Zależności
```
D = sym({
(a,b), (a,c), (a,c), (a,f), (b,c), (b,d), 
(b,f), (c,e), (d,e), (d,f), (e,f)
}) + Identyczności

I = {
(a,d), (b,e), (c,d), (c,f)
}
```

Przy szukaniu zależności:

```
1. Iterujemy przez każdą operacje od lewej
2. Dla każdej iterowanej operacji cofamy się i szukamy zależności
3. Trzeba też sprawdzić czy dana operacja nie ma już zależności pośredniej!
```