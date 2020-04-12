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
				<li><a href="#Third_Sub_Point_7">3.7 Status zamówienia</a></li>
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
				<li><a href="#Sewenth_Sub_Point_2">7.2 Zmiana kolejności
						cech produktu</a></li>
				<li><a href="#Sewenth_Sub_Point_3">7.3 Edycja cechy
						produktu</a></li>
				<li><a href="#Sewenth_Sub_Point_4">7.4 Uzupełnianie
						zamówień o nowe cechy produktu</a></li>

			</ul>
			<li><a href="#Eight_Point_Header">8. Opcja (cecha), Feature</a></li>
			<ul>
				<li><a href="#Eighth_Sub_Point_1">8.1 Edycja opcji</a></li>
				<li><a href="#Eighth_Sub_Point_2">8.2 Tworzenie nowej opcji</a></li>
			</ul>

			<li><a href="#Ninth_Point_Header">9. Edycja wielu produktów,
					MultiEdit</a></li>
			<ul>
				<li><a href="#Ninth_Sub_Point_1">9.1 Dodawanie opcji do
						wielu produktów</a></li>
				<li><a href="#Ninth_Sub_Point_2">9.1 Usunięcie opcji z
						wielu produktów</a></li>
			</ul>
			<li><a href="#Tenth_Point_Header">10. Baza danych</a></li>
			<ul>
				<li><a href="#Tenth_Sub_Point_1">10.1 Export bazy danych do
						pliku</a></li>
				<li><a href="#Tenth_Sub_Point_2">10.2 Wczytanie bazy danych
						z pliku</a></li>
			</ul>

			<li><a href="#Eleventh_Point_Header">11. Import z Matrixa</a></li>
			<ul>
				<li><a href="#Eleventh_Sub_Point_1">11.1 Wczytanie pliku
						Matrixa na server</a></li>
				<li><a href="#Eleventh_Sub_Point_2">11.2 Wczytanie
						informacji dla zamówienia z Matrixa</a></li>
			</ul>

			<li><a href="#Twelfth_Point_Header">12. Użytkownik</a></li>
			<ul>
				<li><a href="#Twelfth_Sub_Point_1">12.1 Nadanie dostępu
						admina</a></li>

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
		<div class="opis">Ukaże się wówczas lista opcji do wyboru:</div>

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

			<div class="opis">Aby zmienić nazwę zamówienia należy kliknąć
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

		<div>
			<h2 id="Third_Sub_Point_7">3.7 Status zamówienia</h2>
			<div class="opis">Na liście zamówień w kolumnie "Progress"
				widoczne są kolorowe symbole, R odpowiada za RS, D za dokumentację,
				Q za QAD, P za produkcję. Kolor czerwony oznacza, że jeszcze nie
				została wykoana praca w danym obszarze. Kolor żółty, że prace
				trwają, zielony że praca została wykonana lub nie jest potrzebna.
				Każdy status można ustawić indywidualnie, ale jeśli wszystko
				przebiega poprawnie statusy powinny zmieniać się "od lewej strony",
				czyli dopóki dokumentacja nie zostanie wykonana nie powinien się
				zminić status QAD z czerwonego itp. Litery bez koloru, na niebieskim
				tle, będą w przypadku gdy zlecenie zostało utworzone przed
				wprowadzeniem statusów do programu. Powyżej tabeli jest lista
				filtrów "to make RS", "to make documentation" itp., kliknięcie
				spowoduje przefiltrowanie listy zleceń wg opisu.</div>

			<img
				class="helpimg"
				src="/im/status.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">


			<div class="opis">Aby zmienić status zamówienia należy wejść w
				widok szczegółowy zamówienia. U góry widoczne sa kolorowe przyciski
				z napisami: "RS", "Doc" itd. Aby zmienic status należy przy
				odpowiednim statusie nacisnąć na szczałkę. Szczałka w prawo zmienia
				status na kolejny w procesie, czyli zmienia ze statusu do zrobienia
				na status zrobione tzn. z koloru zótego na czerwony.</div>

			<img
				class="helpimg"
				src="/im/status2.png"
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

			<div class="opis">
				Wówczas przejdziemy do formularza, przy użyciu którego możemy
				zdefiniować nową cechę produktu. Nowa cecha produktu będzie dodana
				do każdego już zdefiniowanego produktu. Poniżej przykład tworzenia
				cechy "Document Box", wpisujemy nazwę i wybieramy w dolnym menu
				opcje, które chcemy aby były dostępne dla tej cechy. Opcję jak tutaj
				"Standard document box" i "Document box" należy zdefiniować
				wcześniej (
				<a href="#Eighth_Sub_Point_2">8.1 Tworzenie nowej opcji</a>
				). Po wypełnieniu formularza klikamy "Save"
			</div>

			<img
				class="helpimg"
				src="/im/pf2.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div class="opis">
				Po dodaniu nowej cechy, jak przejdziemy do tworzenia nowego
				zamówienia, cecha powinna być dostępna na samym dole. Należy
				pamiętac, że ta nowa cecha nie zostanie dodana do skonfigurowanych
				już zamówień, w każdym zamówieniu trzeba je wybrać indywidualnie
				poprzez przycisk "update" (
				<a href="#Sewenth_Sub_Point_4">7.4 Uzupełnianie zamówień o nowe
					cechy produktu</a>
				).
			</div>

			<img
				class="helpimg"
				src="/im/pf3.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">
		</div>

		<div>
			<h2 id="Sewenth_Sub_Point_2">7.2 Zmiana kolejności cech produktu</h2>
			<div class="opis">Czasem może być konieczność zmiany kolejności
				cech produktu. Czyli np. jak po stworzeniu nowej cechy, pojawia się
				ona na samym końcu, jeśli chcemy np. żeby była ta cecha piąta od
				końca to przechodzimy do linka "PF Move" w menu górnym. Pojawi się
				lista wszystkich cech w aktualnej kolejności.</div>

			<img
				class="helpimg"
				src="/im/pf4.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div class="opis">Szukamy odpowiedniej cechy, w tym przypadku
				jedziemy na sam dół i klikamy strzałkę skierowaną do góry.</div>

			<img
				class="helpimg"
				src="/im/pf5.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">


			<div class="opis">Po kliknięciu strzałki strona musi się
				przeładować, a cecha przesunie się o jedną pozycję do góry. Jeśli
				cecha ma być piąta od końca musimy powtórzyć operację aż do
				uzyskania oczekiwanego efektu.</div>

			<img
				class="helpimg"
				src="/im/pf6.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

		</div>

		<div>
			<h2 id="Sewenth_Sub_Point_3">7.3 Edycja cechy produktu</h2>

			<div class="opis">W celu edycji cechy przechodzimy do linka "PF
				Move" w menu górnym. Pojawi się lista wszystkich cech w aktualnej
				kolejności. Aby skasować cechę skonfigurowana we wszystkich
				produktach klikamy na kosz z prawej strony. W celu edycji np. nazwy
				klikamy na nazwe cechy z symbolem edycji</div>

			<img
				class="helpimg"
				src="/im/pf4.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div class="opis">Wówczas przechodzimy do menu podobnego jak
				przy tworzeniu cechy, możemy zmienić nazwę i kliknąć przycisk
				"Save". Nazwa zostanie zmieniona również na już istniejacych
				zamówieniach.</div>

			<img
				class="helpimg"
				src="/im/pf7.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

		</div>
		<div>
			<h2 id="Sewenth_Sub_Point_4">7.4 Uzupełnianie zamówień o nowe
				cechy produktu</h2>

			<div class="opis">Gdy stworzymy nową cechę prodyktu, nie doda
				się z automatu do skonfigurowanych już zamówień. Jeśli chcemy
				uzupełnić już istniejące zamówienie o tą cechę należy przejść do
				widoku szczegółowego zamówienia. I kliknąć na przycisk "update"</div>

			<img
				class="helpimg"
				src="/im/pf8.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">
			<div class="opis">Pokarze się wówczas formularz z opcjami które
				nie sa zdefiniowane w danym zamówieniu, należy wybrać odpowiednią i
				kliknąc "Save". W zamówieniu zostanie uzupelniona ta cecha.</div>

			<img
				class="helpimg"
				src="/im/pf9.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">
		</div>
	</div>
	<div>
		<h2 id="Eight_Point_Header">8. Opcja (cecha), Feature</h2>

		<div class="opis">Listę wszystkich opcji można znaleźć pod
			linkiem "Features" w menu górnym. Aby edytowań nalezy wybrać ikone po
			prawej z symbolem edycji. Symbol zegarka pokazuje zmiany jakie były
			wprowadzone do danej opcji. Zmiany te są w formie pliku JSON, jego
			wyświetlanie zależy od przeglądarki lub wtyczek w niej
			zainstalowanych. Kosz na śmieci służy do kasowania. Skasować da się
			jedynie opcje, które nie są nigdzie użyte.</div>

		<img
			class="helpimg"
			src="/im/f1.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div>
			<h2 id="Eighth_Sub_Point_1">8.1 Edycja opcji</h2>
			<div class="opis">
				Edycja jest bardzo podobna do
				<a href="#Forth_Sub_Point_1">4.1 Edycja niestandardowej opcji</a>
				, można także zmienić obraz jak w punkcie
				<a href="#Forth_Sub_Point_2">4.2 Edycja obrazu opcji</a>
				. Aby znależć daną opcję np. w celu edycji można ją wyszukać
				wciskajac Ctrl+F i wpisując jej nazwę.
			</div>

			<img
				class="helpimg"
				src="/im/f2.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">
		</div>
		<div>
			<h2 id="Eighth_Sub_Point_2">8.2 Tworzenie nowej opcji</h2>

			<div class="opis">
				Tworzenie nowej opcji jest przydatne np. w przypadku gdy chcemy
				dodać nową cechę produktu
				<a href="#Sewenth_Sub_Point_1">7.1 Dodanie cechy produktu.</a>
				. W tym celu klikamy przycisk "F+" w menu górnym. Formularz wygląda
				jak niżej, na tym etapie nie można dodać obrazka, jedynie wpisać
				nazewę pliku znajdującego się już na serwerze. Aby dodać obraz
				poprzez wklejenie nalezy edytować ponownie daną opcję.
			</div>

			<img
				class="helpimg"
				src="/im/f3.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

		</div>

	</div>
	<div>
		<h2 id="Ninth_Point_Header">9. Edycja wielu produktów, MultiEdit</h2>
		<div class="opis">W celu ułatwienia edycji i przeglądania już
			skonfigurowanych produktów można użyć funkcji Multiedit do której
			link znajduje się w menu górnym. Po kliknięciu w link ukaże się
			formularz podobny do tego przy tworzeniu zamówienia, ale służy do
			filtrowania produktów wg wybranych opcji. Czyli jeżeli wybierzemy w
			cesze koła: rozmiar 385/65 R22,5, to po przefiltrowaniu dostaniemy
			jedynie te produkty, w których są takie koła dostępne. Co umożliwi
			nam np. uzupełnienie nowego rodzaju opony dla wszystkich produktów
			jednocześnie. Po wybraniu rządanych wartości filtra, należy kliknąć
			przycisk "Search".</div>

		<img
			class="helpimg"
			src="/im/multi1.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Jeśli przefiltrujemy tylko po rozmiarze koła
			385/65 R22,5, zobaczymy dużą tabelę z wszystkimi cechami produktów.</div>

		<img
			class="helpimg"
			src="/im/multi2.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div class="opis">Następnie klikając na kafelki "Wheels" i
			"Tires", przefiltrujemy wyświetlaną zawartość, tak aby były widoczne
			tylko interesujące nas cechy. Należy zwrócić uwagę na wartość przy
			"Edit in all products in:", jeżeli ostatnim klikniętym kafelkiem była
			cecha "Tires" taka tam się wyświetli. Oznacza to, że jak klikniemy
			przyciski "Add feature", do takiej cechy w wyświetlonych produktach,
			będzie można dodać wybraną opcja. Jak klikniemy "Remove feature",
			będzie można usunąć z tej cechy wybraną opcję. "Show all" resetuje
			widok do wszystkich widocznych cech, przydatne gdy wybierzemy za dużo
			lub chcemy sprawdzić coś innego.</div>

		<img
			class="helpimg"
			src="/im/multi3.png"
			alt="Flowers in Chania"
			onerror="imgError(this)">

		<div>
			<h2 id="Ninth_Sub_Point_1">9.1 Dodawanie opcji do wielu
				produktów</h2>

			<div class="opis">Jeżeli przefiltrujemy opcję jak w punkcie
				powyżej możemy dodać np., nowy rodzaj opony do wszystkich produktów,
				w których jest użyta odpowiednia opona.</div>

			<img
				class="helpimg"
				src="/im/multi4.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div class="opis">Po kliknięciu przycisku "Add feature" pojawi
				się menu w którym możemy wybrać opcję jaką chcemy dodać. Opcje są
				wyświetlane na podstawie wszystkich produktów dotychczas
				skonfigurowanych. Czyli jak mamy zupełnie nową opcję, którą chcemy
				dodać. Musimy skonfigurować najpierw co najmniej jeden produkt z tą
				opcją, aby mogła się pojawic na tej liście.</div>

			<img
				class="helpimg"
				src="/im/multi5.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div class="opis">Po dodaniu, dodana opcja będzie zaznaczona na
				kolor żółty. Po odświeżeniu strony zostaną zaczytane opcje na nowo i
				w standardowym kolorze.</div>

			<img
				class="helpimg"
				src="/im/multi8.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">



		</div>

		<div>
			<h2 id="Ninth_Sub_Point_2">9.1 Usunięcie opcji z wielu produktów</h2>

			<div class="opis">Po kliknięciu przycisku "Remove feature".</div>

			<img
				class="helpimg"
				src="/im/multi7.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">


			<div class="opis">Po usunięcieu, usunięta opcja będzie
				zaznaczona na kolor czerwony z przekreślonym tekstem. Po odświeżeniu
				strony zostaną zaczytane opcje na nowo, już bez tych opcji. Jeżeli
				usuwana opcja jest jedyną zdefiniowaną w produkcie, nie zostanie
				usunięta! Należy najpierw dodać inną, która pozostanie po usunięciu.
			</div>

			<img
				class="helpimg"
				src="/im/multi6.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

		</div>


	</div>
	<div>
		<h2 id="Tenth_Point_Header">10. Baza danych</h2>
		<div class="opis">Przy wykonywaniu operacji na bazie danych
			należy mieć świadomość, że inni użytkownicy mogą pracować w tym samym
			czasie na obecnej bazie danych. Wczytanie starej bazy danych powoduje
			utratę danych, które były wprowadzone przed jej exportem. Zaleca się
			robienie kopi zapasowej bazy danych co jakiś czas, lub przy
			wprowadzaniu większych zmian do konfiguracji produktów. Należy unikać
			wczytywania bazy danych.</div>
		<div>
			<h2 id="Tenth_Sub_Point_1">10.1 Export bazy danych do pliku</h2>
			<div class="opis">W celu zapisania aktualnej bazy danych należy
				kliknąc link "DB" z sybolem dyskietki ze szczałką skierowaną w dół.
				Po kliknięciu automatucznie zostanie pobrany plik o nazwie
				"db-dump.sql" do folderu domyślnego dla wszystkich pobieranych
				plików z przeglądarki, zwykle do folderu "Pobrane".</div>

			<img
				class="helpimg"
				src="/im/db1.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

		</div>

		<div>
			<h2 id="Tenth_Sub_Point_2">10.2 Wczytanie bazy danych z pliku</h2>
			<div class="opis">Przed wczytaniem bazy danych zaleca się
				zrobienie kopii aktualnej bazy danych. W celu wczytania zapisanej
				bazy danych należy kliknąc link "DB" z sybolem dyskietki ze szczałką
				skierowaną do góry. Ukaże się menu do inportu pliku, w którym należy
				kliknąć przycisk "Wybierz plik".</div>

			<img
				class="helpimg"
				src="/im/db2.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div class="opis">Otworzy się okno gdzie możemy wybrać plik
				jaki chcemy wczytać.</div>

			<img
				class="helpimg"
				src="/im/db3.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div class="opis">Po wybraniu pliku i wciśnięciu przycisku
				"Submit", pojawi się widok potwierdzający wczytanie tego pliku.</div>

			<img
				class="helpimg"
				src="/im/db4.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

		</div>

		<div>
			<h2 id="Eleventh_Point_Header">11. Import z Matrixa</h2>
			<div class="opis">Istnieje możliwość zaimportowania informacji
				z pliku sharpointa, z tzw. Matrixa. Wymaga to jednak zaimportowania
				aktualnego pliku do programu. Importowane wartości są ściśle
				powiązane z rozmieszczeniem kolumn w oryginalnym pliku, dodanie lub
				przesunięcie kolumn może wiązać się z tym, że nie będzie działać ten
				import poprawnie. Informację jakie można zaimportować to numery
				polskich zleceń i link do materiałów z sherpointa powiązanych z tym
				zamówieniem.</div>
			<img
				class="helpimg"
				src="/im/matrix1.png"
				alt="Flowers in Chania"
				onerror="imgError(this)">

			<div>
				<h2 id="Eleventh_Sub_Point_1">11.1 Wczytanie pliku Matrixa na
					server</h2>

				<div class="opis">Wczytanie pliku Matrixa wymaga zapisania go
					na swoim komputerze i następnym wczytaniu go do programu. Aby
					wczytać plik należy wybrać link "Matrix" z sybmolem dyskietki ze
					strzałką skierowaną ku górze, z menu górnego.</div>
				<img
					class="helpimg"
					src="/im/matrix2.png"
					alt="Flowers in Chania"
					onerror="imgError(this)">

				<div class="opis">Następnie należy kliknąć przycisk "Wybierz
					plik" i wybrać zapisany na dysku plik Matrixu, kliknąć "Otwórz",
					potwierdzić przyciskiem "Submit". Pojawi się potwierdzenie
					wczytania pliku.</div>
				<img
					class="helpimg"
					src="/im/matrix3.png"
					alt="Flowers in Chania"
					onerror="imgError(this)">

				<div>
					<h2 id="Eleventh_Sub_Point_2">11.2 Wczytanie informacji dla
						zamówienia z Matrixa</h2>

					<div class="opis">Aby wczytać informację z Matrixa należy
						przejść do widoku szczegółowego zamówienia i wybrać ikonę z
						symbolem magnesu. Istotnę jest aby nazwa zamówienia w kolumnie C w
						pliku Matrixa była zgodna z nazwą zapisanego zamówienia.</div>
					<img
						class="helpimg"
						src="/im/matrix4.png"
						alt="Flowers in Chania"
						onerror="imgError(this)">

					<div class="opis">Po chwili jeżeli dane zostaną odnalezione i
						wczytane informację o numerach polskich zleceń i linku do folderów
						z sharpointa pojawią się na widoku szczegółowym zamówienia.</div>
					<img
						class="helpimg"
						src="/im/matrix5.png"
						alt="Flowers in Chania"
						onerror="imgError(this)">

				</div>

			</div>

		</div>

		<div>
			<h2 id="Twelfth_Point_Header">12. Użytkownik</h2>

			<div>
				<h2 id="Twelfth_Sub_Point_1">12.1 Nadanie uprawnień admina</h2>

				<div class="opis">Nadać uprawnienia admina może jedynie użytkownik,
					który posiada już dostep "ADMIN". W tym celu należy kliknąć w
					przycisk "Users" w menu górnym. Przejdziemy wówczas do listy
					użytkowników. W karcie użytkownika możemy zobaczyć jakie
					uprawnienia ma dany użytkownik. Aby zmienic uprawnienia dla danego
					użytkownika należy kliknąc w przycisk "Give authority"na karcie
					danego użytkownika.</div>
				<img
					class="helpimg"
					src="/im/admin.png"
					alt="Flowers in Chania"
					onerror="imgError(this)">
				<div class="opis">Przejdziemy do formularza gdzie możemy nadać
					uprawnienia. Aby nadać uprawnienia aministratora należy wpisać
					słowo "ADMIN" zwracając uwage na wielkość liter. Jeśli chcemy
					zabrać uprawnienia to usunamy wszystko i klikamy zapisz.</div>
				<img
					class="helpimg"
					src="/im/admin3.png"
					alt="Flowers in Chania"
					onerror="imgError(this)">


			</div>


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