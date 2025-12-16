# Zadanie 7

## 1. Zdefiniowanie podstawowych niepodzielnych zadań obliczeniowych

Krokiem pierwszym jest rozpoznanie i nazwanie pojedyńczych operacji jakie zachodzą w algorytmie eliminacji Gaussa.

---

### Operacje

- **A(i, k)**

Znaleznienie mnożnika wiersza `i` dla odejmowanego wiersza `k`

**$m(k,i) = M(k,i) / M(i,i)$**

- **B(i, j, k)**

Pomnożenie elementu `j` w wierszu `i` przez mnożnik, aby później wykorzystać go w odejmowaniu dla kroku `k`

**$n(k,i) = M(i,j) \cdot m(k,i)$**

- **C(i, j, k)**

Odjęcie pomnożonej w operacji `B` wartości dla elementu `j` w wierszu `k` na pozycji `j`.

**$M(k,j) = M(k,j) - n(k,i)$**

---

## 2. Konstrukcja alfabetu Σ

Znając definicje niepodzielnych zadań które są nam potrzebne w celu przeprowadzenia algorytmu eliminacji gaussa, możemy określić jakie operacje wystąpią w przypadku wykonywania algorytmu dla macierzy `N x N`

---

- #### Wykonania operacji `A`

Operacja A wykonuje się dla każdego wiersza, ponieważ dla każdej coraz mniejszej podmacierzy potrzebujemy nowego mnożnika.

> Wyjątkiem jest ostatni wiersz dla którego algorytmu już nie wykonujemy. Wszystkie wiersze do tego momentu są już wyznaczone i algorytm kończy działanie.

```
∀i ∈ {1 ... N-1}
∀k ∈ {i+1 ... N}

-   { A(i,k) } ∈ Σ
```

- #### Wykonania operacji `B`

Operacja B wykonuje się dla każdego A i iteruje po każdym elemencie zaczynając od indeksu `i`

```
∀i ∈ {1 ... N-1}
∀k ∈ {i+1 ... N}
∀j ∈ {i ... N+1}

-   { B(i,j,k) } ∈ Σ
```

> `j + 1` ponieważ nasza Macierz ma doklejoną kolumnę wynikową zatem z `NxN` tworzy się rozmiar `NxN+1`

- #### Wykonania operacji `C`

Operacja C wykorzystuje pomnożoną wartość otrzymaną z operacji B i odejuje ją od kolumny `i` wiersza `k`.

```
∀i ∈ {1 ... N-1}
∀k ∈ {i+1 ... N}
∀j ∈ {i ... N+1}

-   { C(i,j,k) } ∈ Σ
```

- #### Ostateczny Alfabet Σ

```
∀i ∈ {1 ... N-1}
∀k ∈ {i+1 ... N}
∀j ∈ {i ... N+1}

-   { A(i,k), B(i,j,k), C(i,j,k) } ∈ Σ
```

---

## 3. Zdefiniowanie zależności

W celu określenia zbioru zadań obliczeniowych opiszemy generator zależności. Rozpatrujemy generacje zależności dla każdego wcześniej zdefiniowanego obliczenia. W ten sposób dostaniemy zbiór $D$, który zawiera wszystkie nieprzechodnie oraz niepodzielne zależności.

- #### Zależności Wewnętrzne

Wynikają z przepływu zmiennych tymczasowych $m(k,i)$ , $n(k,i)$ w tym samym kroku $i$

---

**$A ➜ B$** 

$B$ potrzebuje mnożnika $m(k,i)$ z $A$

```
∀i ∈ {1 ... N-1}
∀k ∈ {i+1 ... N}
∀j ∈ {i ... N+1}

-   ( A(i,k), B(i,j,k) ) ∈ D
```

---

**$B ➜ C$**

$C$ potrzebuje iloczynu $n(k,i)$ z $B$

```
∀i ∈ {1 ... N-1}
∀k ∈ {i+1 ... N}
∀j ∈ {i ... N+1}

-   ( B(i,j,k), C(i,j,k) ) ∈ D
```

---

- #### Zależności międzyetapowe

Między iteracjami **$i$** (obecną), a **$i-1$** (poprzednią)

---

**$C(i-1) ➜ A(i)$**

$A(i,k)$ wymaga aktualnych wartości $M(i,i)$ oraz $M(k,i)$

```
∀i ∈ {2 ... N-1}
∀k ∈ {i + 1 ... N}

-   ( C(i-1,i,i), A(i,k) ) ∈ D 
    // Aktualizacja M(i,i)

-   ( C(i-1,i,k), A(i,k) ) ∈ D 
    // Aktualizacja M(k,i)
```

---

**$C(i-1) ➜ C(i)$**

Musimy zapobiec możliwości sekwencyjnego zapisu tego samego elementu $M(k,j)$

```
∀i ∈ {2 ... N}
∀j ∈ {i ... N}
∀k ∈ {i + 1 ... N}

-   ( C(i-1,j,k), C(i,j,k) ) ∈ D
```

---

**$C(i-1) ➜ B(i)$**

Wartość $M(i,j)$ musi zostać zaktualizowana zanim zostanie użyta do mnożenia w zadaniu $B$

```
∀i ∈ {2 ... N}
∀j ∈ {i ... N}
∀k ∈ {i ... N}

-   (C(i-1,j,i), B(i,j,k) ) ∈ D
```

---

W ten sposób otrzymujemy generator tworzący **zbiór zależności $D$**

---

## 4. Zdefiniowanie postaci normalnej Foaty

Postać Normalna Foaty jest iloczynem klas niezależności $Fm$, gdzie każda klasa jest zbiorem zadań, które w danym kroku mogą być wykonywane całkowicie równolegle.

Liczba klas Foaty dla macierzy $N x N$ wynosi $M = 3 * (N-1)$. 

Postać normalna Foaty jest iloczynem tych klas dla każdego etapu eliminacji $i ∈ {1, ..., N-1}$.

$$t = \prod_{i=1}^{N-1} [F_{3i-2}]_{\equiv_I^+} \cdot [F_{3i-1}]_{\equiv_I^+} \cdot [F_{3i}]_{\equiv_I^+}$$

---

### Wyjaśnienie powyższego wyprowadzenia

- **Klasa Mnożników $F(3i-2)$**

Wszystkie zadania $A$ w danym kroku $i$ są od siebie niezależne

```
F(3i-2) = { A(i, k) | k ∈ {i+1 ... N} }
```

- **Klasa Iloczynów $F(3i-1)$**

Wszystkie zadania $B$ dla danego kroku $i$ są od siebie niezależne, ponieważ zapisują różne zmienne tymczasowe $n(k,i)$

```
F(3i-1) = { B(i, j, k) | k ∈ {i+1 ... N}, j ∈ {i ... N+1} }
```

- **Klasa Odejmująca $F(3i)$**

Wszystkie zadania $C$ dla danego kroku $i$ są od siebie niezależne, ponieważ modyfikują różne elementy $M(k,j)$

```
F(3i)   = { C(i, j, k) | k ∈ {i+1 ... N}, j ∈ {i ... N+1} }
```

---

## 5. Graf Zależności Diekerta $G_D$ dla $NxN$

Graf Diekerta $G_D$ jest grafem skierowanym, który reprezentuje porządek częściowy wymuszony przez relację zależności $D$ algorytmu eliminacji Gaussa.

$$G_D = (Σ, E)$$

### 5.1. Zbiór Wierzchołków ($Σ$)

Zbiór wierzchołków $G_D$ jest tożsamy z **Alfabetem** $Σ$ (zgodnie z sekcją 2), zawierającym wszystkie niepodzielne zadania $A, B, C$.

$$Σ = \{ A_{i, k} \} ∪ \{ B_{i, j, k} \} ∪ \{ C_{i, j, k} \}$$

```markdown
// Warunki indeksów definiujące wierzchołki:
∀i ∈ {1 ... N-1}, k ∈ {i+1 ... N}, j ∈ {i ... N+1}
```

### 5.2. Zbiór Krawędzi ($E$)

Zbiór krawędzi $E$ jest tożsamy z **minimalną, nieprzechodnią relacją zależności** $D$ (zgodnie z sekcją 3). Krawędź istnieje tylko, jeśli występuje bezpośredni konflikt danych.

$$E = D$$

#### Generator Krawędzi $E$:

1.  **Zależności Wewnętrzne ($A ➜ B, B ➜ C$):**

    ```
    // A ➜ B
    ∀i ∈ {1 ... N-1}, k ∈ {i+1 ... N}, j ∈ {i ... N+1}: 
    ( A(i,k), B(i,j,k) ) ∈ E

    // B ➜ C
    ∀i ∈ {1 ... N-1}, k ∈ {i+1 ... N}, j ∈ {i ... N+1}: 
    ( B(i,j,k), C(i,j,k) ) ∈ E
    ```

2.  **Zależności Międzyetapowe ($C_{i-1} ➜ ...$):**

    ```
    // C(i-1) ➜ A(i)
    ∀i ∈ {2 ... N-1}, k ∈ {i+1 ... N}:
    -   ( C(i-1,i,i), A(i,k) ) ∈ E
    -   ( C(i-1,i,k), A(i,k) ) ∈ E

    // C(i-1) ➜ C(i)
    ∀i ∈ {2 ... N-1}, j ∈ {i ... N+1}, k ∈ {i+1 ... N}:
    -   ( C(i-1,j,k), C(i,j,k) ) ∈ E

    // C(i-1) ➜ B(i)
    ∀i ∈ {2 ... N-1}, j ∈ {i ... N+1}, k ∈ {i+1 ... N}:
    -   (C(i-1,j,i), B(i,j,k) ) ∈ E
    ```

Graf Diekerta $G_D$ jest grafem oznaczonym, a ślad $t$ jest zbiorem wszystkich ciągów symboli, które są poprawnymi ścieżkami w tym grafie (lub równoważnymi ciągowi $w$ poprzez zamianę niezależnych symboli).

Poniżej znajduje się wygenerowany graf Diekerta dla **macierzy 4x4**

![Graf Diekerta](./assets/Graf%20Diekerta.png)

# Implementacja

Zadanie zostało wykonane z wykorzystaniem `Java - Gradle 25`

Znajduje się ono w folderze `elimination`

## Uruchamianie

1. Określ path do pliku wejściowego (pliki znajdują się w folderze `data`)

2. Uruchom skrypt `Runner`

3. Macierz po eliminacji Gaussa zostanie wyświetlona na standardowym wyjściu.