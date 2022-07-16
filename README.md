# Obsluga-promow-w-przystani
Programowanie współbieżne

Projekt oparty na programowaniu współbieżnym. Rozwiązywany jest problem
Producenta i Konsumenta. Wykorzystanie strukturalnych mechanizmów
synchronizacji danych. Podejście obiektowe z wykorzystaniem założeń
paradygmau OOP. Graficzne zobrazowanie działania procesów
współbieżnych z możliwością modyfikacji danych wejściowch poprzez GUI.
Zastosowana technologia JavaFX

Założenia programu:
Przeprawę obsługuje N promów o określonej pojemności każdy.Na przystań i z
przystani prowadzi jedna droga, po której poruszają się pojazdy z różną prędkością.
Przystań ma ograniczoną pojemność. Na promy jesttylko jeden wspólny wjazd i
zjazd, przez który samochody wjeżdżają/zjeżdżają pojedynczo(pierwszeństwo
samochodów zjeżdżających).Promy czekają na zapełnienie przez określony
maksymalny czas, po którym:
-rozpoczynają przeprawę, gdy na pokładzie znajduje się co najmniej jeden pojazd,
-lub rozpoczynają kolejny okres oczekiwania.Jeżeli zapełnią się wcześniej, to
rozpoczynają przeprawę przed upływem tego czasu.
W przypadku przybycia promu do przystani, gdy jest zajęta przez inny prom, to
oczekuje w kolejce.

Syntetyczny opis problemu:
Promy poruszają się po rzece, maksymalna ilość promów jaką program graficzny jest
w stanie przedstawić to siedem. Każdy prom porusza się z jednakową prędkością,
którą można parametryzować. Prom może przyjąć ograniczoną liczbę samochodów.
Samochody są produkowane, wjeżdżają na parking, gdzie oczekują na możliwość
wjechania na prom. Parking ma ograniczoną ilość samochodów, jeśli jest pełny, to
produkcja samochodów jest wstrzymana.
W danej chwili może tylko jeden prom być w przystani i pobierać samochody na
pokład. Jeśli w tym czasie kolejny prom chciałby pobierać samochody, to musi się
ustawić w kolejce, tuż za promem, który jest przed nim.
W przypadku, gdy produkcja samochodów trwa zbyt wolno jest możliwość odjechania
promu od przystani wcześniej, nawet jeśli nie jest pełny pokład, o ile został
przekroczony maksymalny czas oczekiwania na zapełnienie. Warunkiem odjechania
od przystani jest minimum jeden samochód na pokładzie.
Promy, które odjeżdżają od przystani poruszają się po rzece, a następnie ponownie
chcą wjechać do przystani.
Jeżeli przypływający prom do przystani posiada samochody na pokładzie, to zaczyna
się wyjazd samochodów, a następnie rozpoczyna się czas załadunku nowych
pojazdów.

Wykaz współdzielonych zasobów:
Współdzielonym zasobem dla promów i przystani jest parking samochodów. Problem
ten można porównać do problemu Producenta i Konsumenta. W naszym przypadku
producentem jest przystań, zaś konsumentem promy. Promy czekają, aż przystań
wyprodukuje samochód, a następnie wstawi go do pamięci współdzielonej – parkingu
samochodów. Po czym Prom zajmuję pamięć współdzieloną i pobiera samochód z
parkingu.
Do współdzielenia zasobów wykorzystałem monitor, jest to strukturalny mechanizm
synchronizacji, którego definicja obejmuje:
- hermetycznie zamkniętą definicje zmiennych,
- definicję wejść, czyli procedur umożliwiających wyłączne wykonanie operacji na
polach monitora.
Wewnątrz monitora może być aktywny co najwyżej jeden proces: proces promu, lub
proces przystani.
W implementacji klasy „Bufor” znajdują się dwie metody:
metoda „wstaw” wykonuje proces przystani, której zadaniem jest wstawienie
samochodu do parkingu. Natomiast metoda „pobierz” wykonywana jest przez Prom,
który samochód pobiera na pokład.
Dla nas, współdzielone zasoby będą to zmienne warunkowe.
Dzięki nim jednocześnie w monitorze znajduje się tylko i wyłącznie jeden proces.

Wykaz wyróżnionych punktów synchronizacji:
Na początku programu powoływane są procesy promów i jednej przystani.
Proces przystani w nieskończonej pętli for() produkuje numerki, które przedstawiają
samochody. Numeracja zaczyna się od numeru 0 i trwa w nieskończoność.
Po wstawieniu numerku do bufora poprzez wykorzystanie funkcji „wstaw”, wątek
zasypia na ustalony okres. Okres zasypiania jest to czas pomiędzy produkcją
samochodów. W tym samym czasie zwalnia dostęp do monitora i będzie mógł go
zająć proces promu.
Procesy promów, w zależności czy są w przystani, będą chciały pobrać numerek,
czyli samochód z bufora i załadować go na swój pokład, gdzie przechowują listę
numerów, czyli samochodów.
Jeżeli wątek promu jest w czasie drogi do przystani, bądź ustawiony w kolejce
promów, to jest przetrzymywany w pętli, aby spał i czekał na swoją kolej.
W momencie powoływania do życia procesów Promów uruchamiane są dodatkowe
procesy odpowiedzialne za animacje statków na rzece.
Ich zadaniem jest wyświetlanie obiektów statków oraz poruszanie się nimi
Każdy statek to jeden wątek, który działa samodzielnie.
Prom jest zbudowany tak, że posiada jeden obiekt typu Rectangle – przedstawiający
statek, oraz 9 mniejszych obiektów typu Rectangle przedstawiających czerwone
samochody. Samochody te są na początku niewidoczne, przez co prom wydaje się
pusty. W chwili załadunku jednego samochodu na pokład, wątek promu ustawia
widoczność jednego samochodu na wartość true, przez co widzimy jak samochód się
pojawia.

Wykaz obiektów synchronizacji:
Proces przystani, w momencie, gdy znajduje się w monitorze i następuje produkcja
jednego samochodu, powołuje do życia wątek samochodu, odpowiedzialny za
animację poruszania się z punktu produkcji, do punktu parkingu samochodów.
Wątek kieruje obiektem samochodu, który zaznaczyłem kolorem pomarańczowym.
Warto zwrócić uwagę na fakt, iż proces Przystani nie czeka, aż samochód zakończy
animację dojazdu, tylko natychmiast zajmuje się produkcją kolejnego samochodu.
Może to doprowadzić, iż samochodów dojeżdzających do parkungu pojawi się kilka
jednocześnie.
Podobnie ma się sytuacja, w momencie wyjazdu samochodów z pokładu promu . W
chwili gdy proces promu znajduje się w monitorze, to w momencie, gdy prom
wyładuje jeden samochód, powoływany jest nowy wątek odpowiedzialny za wyjazd
samochodu.

