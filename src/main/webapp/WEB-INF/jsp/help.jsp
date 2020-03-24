<%@ page
	contentType="text/html;charset=UTF-8"
	language="java"%>
<div>
	<button
		onclick="topFunction()"
		id="myBtn"
		title="Go to top">Top</button>

	<div id="toc_container">
		<p class="toc_title">Instrukcja konfiguratora LD</p>
		<ul class="toc_list">
			<li><a href="#First_Point_Header">1. Menu górne</a></li>
			<li><a href="#Second_Point_Header">2. Tworzenie i edycja
					zamówienia</a>
				<ul>
					<li><a href="#Second_Sub_Point_1">2.1 Tworzenie nowego
							zamówienia</a></li>
					<li><a href="#Second_Sub_Point_2">2.2 Tworzenie zamówienia
							niestandardowego</a></li>
				</ul></li>
			<li><a href="#Third_Point_Header">3. Widok zamówienia</a></li>
			<ul>
				<li><a href="#Third_Sub_Point_1">3.1 Widok listy zamówień</a></li>
				<li><a href="#Third_Sub_Point_2">3.2 Zmiana nazwy
						zamówienia</a></li>
				<li><a href="#Third_Sub_Point_3">3.3 Widok szczegółowy
						zamówienia</a></li>
				<li><a href="#Third_Sub_Point_4">3.4 Kasowanie zamówienia</a></li>
				<li><a href="#Third_Sub_Point_5">3.5 Edycja koloru w
						zamówieniu</a></li>
				<li><a href="#Third_Sub_Point_6">3.6 Wydruk zamówienia</a></li>
			</ul>
			<li><a href="#Forth_Point_Header">4. Edycja zamówienia</a></li>
			<ul>
				<li><a href="#Forth_Sub_Point_1">4.1 Edycja niestandardowej
						opcji</a></li>
				<li><a href="#Forth_Sub_Point_2">4.2 Edycja obrazu opcji</a></li>
				<li><a href="#Forth_Sub_Point_3">4.3 Dodanie
						niestandardowej opcji do zamówienia</a></li>
				<li><a href="#Forth_Sub_Point_4">4.4 Zapisanie
						niestandardowwego zamówienia jako produktu.</a></li>
			</ul>

			<li><a href="#Fifth_Point_Header">5. Produkt</a></li>
			<ul>
				<li><a href="#Fifth_Sub_Point_1">5.1 Zapisane produkty</a></li>
				<li><a href="#Fifth_Sub_Point_2">5.2 Edycja produktu</a></li>

			</ul>
			<li><a href="#Sixth_Point_Header">6. Kolory</a></li>
			<ul>
				<li><a href="#Sixth_Sub_Point_1">6.1 Lista kolorów</a></li>
				<li><a href="#Sixth_Sub_Point_2">6.2 Dodanie koloru</a></li>
				<li><a href="#Sixth_Sub_Point_3">6.3 Edycja koloru</a></li>

			</ul>
			<li><a href="#Sewenth_Point_Header">7. Cecha produktu,
					Product Feature</a></li>
			<ul>
				<li><a href="#Sewenth_Sub_Point_1">7.1 Dodanie cechy
						produktu.</a></li>
				<li><a href="#Sewenth_Sub_Point_2">7.2 Edycja cechy
						produktu</a></li>

			</ul>
		</ul>
	</div>

	<div>
		<h2 id="First_Point_Header">1. Menu górne</h2>

		<img
			class="helpimg"
			src="/im/menu_top.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Menu górne zawiera linki do funkcji w
			programie, użytkownicy bez specjalnego dostępu mają widoczne tylko
			białe linki, żółte są dla użytkowników z dostępem "ADMIN".
			Poszczególne linki będą omówione w poniższych podpunktach.</div>
	</div>

	<div>
		<h2 id="Second_Point_Header">2. Tworzenie i edycja zamówienia</h2>
		<div>
			<h2 id="Second_Sub_Point_1">2.1 Tworzenie zamówienia
				standardowego</h2>

			<div class="opis">W celu utworzenia zlecenia wybieramy poniżej
				zaznaczony znak plusa w menu górnym:</div>


			<img
				class="helpimg"
				src="/im/menuorderplus.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">
		</div>
		<div class="opis">Ukarze się wówczas lista opcji do wyboru:</div>

		<img
			class="helpimg"
			src="/im/konfzle.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">
	</div>

	<div class="opis">Ukaże się wówczas lista opcji do wyboru, należy
		wybrać odpowiednią wartość dla każdej z opcji. Wybrana opcja zmieni
		kolor na zielony, automatycznie zostaną wykluczone inne niedostępne
		opcję. Jeśli w którejś z opcji zostanie tylko jedna opcja, zostanie
		ona wybrana i zaznaczona również na zielono, należy sprawdzić czy te
		opcje sa odpowiednie. Jeśli automatycznie zostanie wybrana opcja
		której nie chcemy, należy skonfigurować niestandardowe zamówienie.
		Jeżeli nie wybieżemy którejś z opcji, nie będziemy w stanie zapisać
		zamówienia.</div>
	<img
		class="helpimg"
		src="/im/elementzlisty.png"
		alt="Flowers in Chania"
		onerror="imgError(this)">
	<div class="opis">Po naciśnięciu przycisku "Save", pojawi się
		formularz w którym możemy wprowadzić nazwe zamówienia np.: A/200192.
		Cena wygeneruje się automatycznie na podstawie ceny jaka jest
		wprowadzona do systemu. Wartości poza nazwą można pominąć.</div>
	<img
		class="helpimg"
		src="/im/saveorder.png"
		alt="Flowers in Chania"
		onerror="imgError(this)">
	<div class="opis">Po ponownym naciśnięciu przycisku "Save",
		przejdziemy do listy zapisanych zamówień:</div>

	<img
		class="helpimg"
		src="/im/orderlist.png"
		alt="Flowers in Chania"
		onerror="imgError(this)">

	<div>
		<h2 id="Second_Sub_Point_2">2.2 Tworzenie zamówienia
			niestandardowego</h2>

		<div class="opis">Zamówienie niestandardowe tworzymy gdy podczas
			konfiguracji zamówienia okazuje się ze nie mamy dostępnej takiej
			opcji jak byśmy chcieli. np.: Gdy przy tworzeniu okazuje się że
			chcemy niestandarde położenie czopa królewskiego, wybieramy z opcji
			dostępnych, najbliższych temu co oczekujemy, klikamy następnie na
			przycisk "NS" obok. Pojawi się wtedy dodatkowe menu w którym możemy
			wpisać rządaną wartość, dobrze jest także zawrzeć dodatkowe
			informację i uwagi przydatne przy analizowaniu konstrukcji. Wpisane
			wartości potwierdzamy przyciskiem "OK".</div>
		<img
			class="helpimg"
			src="/im/notokno.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">
		<div class="opis">Pojawi się wówczas informacją w kolumnie obok,
			że wprowadziliśmy niestandardową opcję, wartość przez nas niechciana
			zostanie ozaczona kolorem czerwonym. Przy opcji "Chassis" też pojawi
			się informacją "No standard", gdyż niestandardowe opcję powodują
			konieczność zweryfikowania konstrkcji ramy naczepy.</div>

		<img
			class="helpimg"
			src="/im/powybraniuos.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">
		<div class="opis">Zamówienie skonfigurowane jako niestandard
			będzie miało żółty symbol "NS" na liście zamówień.</div>
		<img
			class="helpimg"
			src="/im/nosorder.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">
	</div>

	<div>
		<h2 id="Third_Point_Header">3. Widok zamówienia</h2>
		<div>
			<h2 id="Third_Sub_Point_1">3.1 Widok listy zamówień</h2>

			<div class="opis">Widok listy zamówień znajduje się pod linkiem
				"Orders" w menu górnym.</div>

			<img
				class="helpimg"
				src="/im/orderwiev.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">
		</div>
		<div>
			<h2 id="Third_Sub_Point_2">3.2 Zmiana nazwy zamówienia</h2>

			<div class="opis">Aby zmienić nazwwe zamówienia należy kliknąć
				na symbol edycji w lini odpowiedniego zamówienia.</div>
			<img
				class="helpimg"
				src="/im/editnazwa.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div class="opis">Przejdziemy wówczas do formularza jak podczas
				tworzenia zamówienia, wktórym możemy zmienić nazwe zamówieniai inne
				wartości.</div>
			<img
				class="helpimg"
				src="/im/editord.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">
		</div>

		<div>
			<h2 id="Third_Sub_Point_3">3.3 Widok szczegółowy zamówienia</h2>
			<div class="opis">Aby otworzyć widok szczegółowy zamówienia
				należy najechać na linie z danym numerem zamówienia i kliknąć.</div>

			<img
				class="helpimg"
				src="/im/zlecenieszcze.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div class="opis">W widoku szczegółowym są dostępne wszystkie
				szczegóły na temat zamówienia imożliwości edycji.</div>

			<img
				class="helpimg"
				src="/im/widokzam.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">


			<div class="opis">Poniżej tabelki z opisem zamówienia jest
				informacja o zmianach jakie były wprowadzone do danego zamówienia,
				kiedy i przez kogo.</div>

			<img
				class="helpimg"
				src="/im/zmzam.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

		</div>


		<div>
			<h2 id="Third_Sub_Point_4">3.4 Kasowanie zamówienia</h2>
			<div class="opis">Istnieje możliwość usunięcia zamówienia przez
				osobę z uprawnieniami "ADMIN". W tym celu należy kliknąć kosz z
				prawej strony (Tylko jak się wie co robi).</div>

			<img
				class="helpimg"
				src="/im/kasowaniezam.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

		</div>


		<div>
			<h2 id="Third_Sub_Point_5">3.5 Edycja koloru w zamówieniu</h2>
			<div class="opis">Aby edytować kolory w zamówieniu należy
				wybrać ikonę z symbolem pędzla na widoku szczegółowym zamówienia.</div>

			<img
				class="helpimg"
				src="/im/kolorzmiana.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div class="opis">
				Pojawi się wtedy formularz z możliwością wyboru kolorów dla
				elmentów, w których jest zdefiniowana informacją o konieczności
				zdefiniowania koloru. Dla elementów które w danym zamówieniu nie
				wymagają malowania, można wybrać opcję "Default", dla elementów z
				nierdzewki opcję "INOX". Po zdefiniowaniu klikamy "Save". Jeżeli
				rządanego koloru nie ma na liście, można stworzyć definicję nowego
				koloru (
				<a href="#Sixth_Sub_Point_2">6.2 Dodanie koloru</a>
				)
			</div>

			<img
				class="helpimg"
				src="/im/kolorwybor.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

		</div>
		<div>
			<h2 id="Third_Sub_Point_6">3.6 Wydruk zamówienia</h2>
			<div class="opis">W celu przejścia do widoku przeznaczonego do
				drukowania należy kliknąć ikonę drukarki.</div>

			<img
				class="helpimg"
				src="/im/drukuj.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">


			<div class="opis">Widok jest czarno biały z niezbędnymi
				informacjami do zamówienia.</div>

			<img
				class="helpimg"
				src="/im/druk.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div class="opis">Aby wydrukować zamówienie należy użyć
				odpowiednich narzędzi dostępnych w przeglądarce.</div>

			<img
				class="helpimg"
				src="/im/browserdruk.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

		</div>
	</div>

	<div>
		<h2 id="Forth_Point_Header">4. Edycja zamówienia</h2>
		<div>
			<h2 id="Forth_Sub_Point_1">4.1 Edycja niestandardowej opcji</h2>
			<div class="opis">Opcję zdefiniowana jak niestandardową należy
				zastąpiś opcją dostępną lub stworzyć opcję spełniająca wymagania
				zapytania o niestandard. Opcje niestandardowe są zaznaczone
				czerwonym kółkiem z wykrzyknikiem, aby edytować wartość należy
				kliknąć na ten symbol.</div>

			<img
				class="helpimg"
				src="/im/nostetitred.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">
			<div class="opis">Pokarzą się dwa okna, w lewym można edytować
				wartości jakie zostały stworzone podczas opisywaniu niestandardu. W
				prawym oknie należy wybrać opcję z już istniejących jeśli taka
				spełnia wymogi, mależy zwrócić uwagę na indeks przy opisie opcji czy
				jest właściwy. Jeśli opis opcji jest za długi, jego część zostanie
				zastąpiona trzema kropkami, jak się kliknie na tą opcję, pełny opis
				zostanie wyświetlony poniżej okna. Czasem opcje z podobnymi opisami
				moga mieć inne indeksy, ale należy się starać aby opis jednoznacznie
				wskazywał na daną ceche. Przyciski "Save" są oddzielne dla lewego i
				prawego okna, należy używać odpowiedniego dla danej opcji.</div>

			<img
				class="helpimg"
				src="/im/editselect.png"
				alt="Flowers in Chania"
				onerror="imgError(this)"> <img
				class="helpimg"
				src="/im/editselect2.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">
			<div class="opis">Jeśli wprowadzimy poprawny opis i wartość
				indeksu, a także uzupełnimy obraz danej opcji w lewym oknie, możemy
				odznaczając element "no standard", aby opcja nie pojawiała się z
				oznaczeniem niestandardu. Należy jednak pamiętać że będzie
				możliwości edycji opcji tylko poprzez "ADMIN"-a, lub zastąpienie
				nową opcją.</div>

			<img
				class="helpimg"
				src="/im/nostandardcheck.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">
		</div>


		<h2 id="Forth_Sub_Point_2">4.2 Edycja obrazu opcji</h2>
		<div class="opis">W celu edycji obrazu przedstawiającego daną
			opcję nalezy kliknąć Image+</div>

		<img
			class="helpimg"
			src="/im/imagePlus.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Pojawi się okno z instrukcją aby wklejić obraz
			ze schowka systemowego poprzez przyciśnięcie klawiszy Ctrl+V</div>

		<img
			class="helpimg"
			src="/im/pastimage.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Używająć Narzędzia Wycinania w windowsie w
			prosty sposób można wklejić dowolny, skopiowany obrazek</div>

		<img
			class="helpimg"
			src="/im/clipa.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Wcisjkająć Ctrl+V na aktywnym oknie obrazek
			zostanie wklejony.</div>

		<img
			class="helpimg"
			src="/im/clip2.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Po wciśnięciu "Save image to feature" zostanie
			zapisany obraz dla danej cechy, w prawym górnym rogu zostanie
			wyświetlona nazwa pod jaka zostanie plik zapisany na serverze. Na
			nazwe pliku składa się nazwa opcji z dołaczoną datą utworzenia
			obrazka.</div>

		<img
			class="helpimg"
			src="/im/clip3.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Po powrocie przyciskiem w przeglądarce do
			poprzedniej strony, nazwa pliku będzie już podpięta i zapisana dla
			danej opcji wyposarzenia. Nie trzeba już dodatkowo klikać przycisku
			"Save" jeżeli nie wprowadziliśmy żadnych dodatkowych zmian.</div>

		<img
			class="helpimg"
			src="/im/clip4.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">



	</div>

	<div>
		<h2 id="Forth_Sub_Point_3">4.3 Dodanie niestandardowej opcji do
			zamówienia</h2>
		<div class="opis">W celu dodania niestandardowej opcji do
			zamówienia nalezy kliknąc ikone w ksztłcie prezentu na widoku
			szczegółowym zamówienia, które chcemy edytować.</div>

		<img
			class="helpimg"
			src="/im/gift2.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Po kliknięciu w prezent, podobne ikony pojawią
			się przy każdej opcji w zamówieniu. W celu edycji wybranej opcji
			należy kliknąć na odpowiednią ikonę przy danej opcji</div>

		<img
			class="helpimg"
			src="/im/gift3.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Przejdziemu wówczas do formularza podobnego
			jak przy edytowaniu opcji niestandardowej. Różnica polega na tym, że
			po lewej stronie nie mamy edycji danej niestandardowej opcji, ale
			możemy stworzyć zupełnie nową na bazie istniejącej. Po prawej możemy
			wybrać z już intniejących opcji.</div>

		<img
			class="helpimg"
			src="/im/gift4.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Po stworzeniu nowej opcji i zapisaniu, ta
			opcja będzie wyświetlać się jako opcja niestandardowa. Dalej należy
			postępować jak w przypadku opcji niestandardowej.</div>

		<img
			class="helpimg"
			src="/im/gift5.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

	</div>
	<div>
		<h2 id="Forth_Sub_Point_4">4.4 Zapisanie niestandardowwego
			zamówienia jako produktu.</h2>

		<div class="opis">Zamówienie niestandardowe jest oznaczone
			zółtym napisem "No standard", jest również symbol dyskietki po jego
			prawej stronie dla użytkowników z uprawnieniami admina. Naciśnięcie
			symbolu dyskietki powoduje zapisanie danego niestandardowanego
			zamówienia jako standardowego produktu. Jeżeli sa jakieś opcje
			niestandardowe, uniemożliwi to zapisanie, należy je edytować, aby
			zawierały poprawne informację i wtedy można próbowac zapisać. Należy
			upewnić się aby wszystkie podane informację były poprawne. Jeżeli
			wprowadziliśmy informację, które okażą się błędne, jedynie admin
			będzie mógł je zmienić.</div>

		<img
			class="helpimg"
			src="/im/nostd2.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Po kliknięciu dyskietki zostaniemy poproszeni
			o potwierdzenie swojej decyzji. Jeżeli by się okazało, że taka
			konfiguracja już istnieje, zostaniemy poinformowani o tym i opis "No
			standard" przestanie być widoczny.</div>

		<img
			class="helpimg"
			src="/im/nostd3.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Jeżeli klikniemy dyskietkę, a jakaą opcja
			będzie opisana jako niestandardowa, dostaniemy o tym komunikat.</div>

		<img
			class="helpimg"
			src="/im/nostd7.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Po potwierdzeniu i zapisaniu nadal pozostanie
			opis "No standard", należy ponownie wcisnąć dyskietkę, aby
			potwierdzić dostępność konfiguracji w zapisanych produktach.</div>

		<img
			class="helpimg"
			src="/im/nostd4.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Jeśli zapis przebieg poprawnie dostaniemy
			komunikat ze taka konfiguracja produktu juz istnieje.</div>

		<img
			class="helpimg"
			src="/im/nostd5.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">


		<div class="opis">Po powrocie do poprzedniej strony zamówienie
			już nie będzie opisane jako "No standard"</div>

		<img
			class="helpimg"
			src="/im/nostd6.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

	</div>

	<div>
		<h2 id="Fifth_Point_Header">5. Produkt</h2>
		<div>
			<h2 id="Fifth_Sub_Point_1">5.1 Zapisane produkty</h2>

			<div class="opis">Aby wyświetliś listę dostępnych produktów
				nalezy kliknąć "Product show" w menu górnym. Z listy produktów można
				wybrać odpowiedni poprzez kliknięcie nazwy. Poprzez symbol teczki
				można stworzyć zamówienie na bazie danej definicji. Symbol
				kopiowania, tworzy identyczną kopie danego pordukty z indetyczną
				nazą z przyrostkiem-"copy". Koszem po prawej można skasować daną
				definicję produktu. Funkcję pod symbolami teczki i kopiowania nie
				powinny być użyteczne, gdyż standardowe funkcje w pełni je
				zastępują. Żółty przycisk "Pattern" z symbolem odświerzania służy do
				aktualizacji domyślnego produktu pattern, należy go użyć jeżeli
				zmienialiśmy coś w definnicji innych produktów, tak aby usunac
				ewentualne usunięte opcje lub dodać jeśli takie dodano. Zwykle
				dzieje się to automatycznie.</div>

			<img
				class="helpimg"
				src="/im/product1.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div class="opis">Product "pattern" zawiera wszystkie dostępne
				opcję, tzn., że opcja "Chassis" zawiera wszystkie dostępne podwozia
				jakie są zawarte w zdefiniowanych produktach i tak podobnie w każdej
				opcji produktu. Tu zdefiniowane opcję są wykorzystane do wybierania
				podczas definiowania zamówienia, nie należy tu ich modyfikować. Wzór
				się aktualizuje zwykle automatycznie, lub można ręcznie w wspomniany
				powyżej sposób.</div>

			<img
				class="helpimg"
				src="/im/pattern1.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">


			<div class="opis">Po kliknięciu nazy produktu zostaniemy
				przeniesieni do formularza gdzie można wprowadzić zmiany w definicji
				produktu. Poprzez kubeł można usunąć dostępną opcję, ale należy
				pamiętać że musi być co najmniej jedna opcja zdefiniowana w danej
				cesze. W cechach jak "Type", "Axle" itp., powinna być tylko jedna
				opcja, w cechach "Front pulpit", "Tires", "Rear lights", itp.,zwykle
				jest więcej niż jedna. Klikając na nazwę cechy zawierającej opcję
				przechodzimy do formularza gdzie możemy dodać inne opcję</div>

			<img
				class="helpimg"
				src="/im/product2.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">
		</div>
		<div>
			<h2 id="Fifth_Sub_Point_2">5.2 Edycja produktu</h2>
			<div class="opis">W formularzu tym mamy pokazane opcje, które
				już są wybrane "Current feature list". Poniżej w długiej liście
				"Change feature list" możemy zaznaczyć jedna lub więcej opcji, którą
				chcemy dodać. List jest długa i zaleca się wyszukanie szukanej opcji
				przy użyciu klawiszy Ctrl+F, co znacznie to ułatwi. Po wybraniu
				opcji do dodania klikamy "Save"</div>

			<img
				class="helpimg"
				src="/im/product3.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

		</div>

	</div>

	<div>
		<h2 id="Sixth_Point_Header">6. Kolory</h2>
		<div>
			<h2 id="Sixth_Sub_Point_1">6.1 Lista kolorów</h2>
			<div class="opis">Lista kolorów znajduje się w menu głównym pod
				linkiem "Colors". Przedstawiony kolor jest tylko symboliczny. Kolory
				można edytować klikając na symbol edycji z prawej strony.</div>

			<img
				class="helpimg"
				src="/im/color1.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">
		</div>
		<div>
			<h2 id="Sixth_Sub_Point_2">6.2 Dodawanie koloru</h2>
			<div class="opis">Klikając symbol "C+" w menu górnym możemy
				przejść do formularza tworzenia coloru. W oknie Hex zaleca się
				dodanie symbolicznego koloru jaki będzie wyświetlany dla danej
				definicji koloru. Aby znaleźć wartośc Hex najłatwiej wpisać w
				przegldarkę google chasła z nazwą koloru np. "Carmin red Hex" i
				wpisać kod jaki się pojawi w formie "#ff0038"</div>

			<img
				class="helpimg"
				src="/im/color3.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">


		</div>
		<div>
			<h2 id="Sixth_Sub_Point_3">6.3 Edycja koloru</h2>
			<div class="opis">Na liście kolorów klikając na symbol edycji
				po prawej stronie koloru przechodzimy do formularza edycji.Formularz
				edycji jest podobny do formularza tworzenia koloru.</div>

			<img
				class="helpimg"
				src="/im/color2.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">


		</div>

	</div>
	<div>
		<h2 id="Sewenth_Point_Header">7. Cecha produktu,Product Feature</h2>
		<div class="opis">Jako ceche produktu rozumie się kategorię
			opisujacą daną grupę opcji dostępnych dla danego produktu np.:
			wysokość siodła, zderzak, lampy, opony, itp.Czyli to co jest w
			poniżej zaznaczonej kolumnie przy tworzeniu zamówienia.</div>

		<img
			class="helpimg"
			src="/im/cecha2.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">
		<div>
			<h2 id="Sewenth_Sub_Point_1">7.1 Dodanie cechy produktu.</h2>
			<div class="opis">W celu dodania nowej cechy należy przejść do
				linka "Product Features" w menu górnym</div>

			<img
				class="helpimg"
				src="/im/pf1.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div class="opis">Wówczas przejdziemy do formularza, przy
				użyciu którego możemy zdefiniować nową cechę produktu. Nowa cecha
				produktu będzie dodana do każdego już zdefiniowanego produktu.
				Poniżej przykład tworzenia cechy "Document Box", wpisujemy nazwę i
				wybieramy w dolnym menu opcję które chcemy aby były dostępne dla tej
				cechy. Opcję jak tutaj "Standard document box" i "Document box" nalezy zdefiniować wcześniej.
				Po wypełnieniu formularza klikamy "Save"</div>

			<img
				class="helpimg"
				src="/im/pf2.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">
		</div>
	</div>
</div>
<script>
	//Get the button
	var mybutton = document.getElementById("myBtn");

	// When the user scrolls down 20px from the top of the document, show the button
	window.onscroll = function() {
		scrollFunction()
	};

	function scrollFunction() {
		if (document.body.scrollTop > 20
				|| document.documentElement.scrollTop > 20) {
			mybutton.style.display = "block";
		} else {
			mybutton.style.display = "none";
		}
	}

	// When the user clicks on the button, scroll to the top of the document
	function topFunction() {
		document.body.scrollTop = 0;
		document.documentElement.scrollTop = 0;
	}
</script>