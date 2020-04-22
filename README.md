Java programozás
================
Féléves feladat
===============


*Könyvtár - könyvek: azonosító, szerző, cím, megjelenés dátuma, státusz (szabad,
kikölcsönzött, selejt)*

Böszörmenyi Daniel

O6385D

2020. április 05.

Az adatszerkezet
================

A program által kezelt adatok szerkezete egy könyvtár leltárát valósítja meg.

Könyvtár - könyvek:

azonosító - Integer

szerző - String

cím - String

megjelenés dátuma – Dátum (/Long)

státusz – String (szabad/kikölcsönzött/selejt)

Megvalósított bemeneti tárolók
==============================

A programban kezelhető fájlok a következők: .csv, .json, .db (SQLite adatbázis
fájl). Ezen fájlok olvasása a programban lévő „Megnyitás” gomb utáni tallózás
lehetőségével történhet, vagy adatbázis kapcsolat esetén az ugyanígy
feliratozott gombra kattintva.

Megvalósított kimeneti tárolók
==============================

A program az általa olvasható összes fájl írására is képes, tehát képes
létrehozni .csv, .json fájlokat is, de új adatbázist is képes készíteni. Ezeket
képes a „Mentés másként…” opció logikája alapján is elkészíteni, de a
felhasználó indítás után választhatja az „Új fájl” létrehozása opciót is, ami
egy üres fájlt hoz létre számára, amit kedve szerinti (az adatszerkezetet
betartva) új adatokkal tölthet fel.

Be- és kimeneti tárolók képekben
================================

![](media/7a59df6fbf7e3f4af19e5ae9a9e27a67.png)

![](media/26530c3c85611cfdb9ba0d9e1f69ea78.png)

![](media/36c2fb811f49daa2f4a9f8f98c58115a.png)

 A program futtatási képei és funkciók ismertetése
==================================================

A program indításkor megjelenő főablaka

![](media/1ece1b02fa1c60247907112fa00a5392.png)

A főablak, miután valamilyen fájlból olvasásra kerültek adatok

![](media/789e66967b494fd0501d4e30ec2f4723.png)

![](media/f3e6330f237c5c3f294ed47ffaf7f2b5.png)

![](media/b00488864e5bb15046b267b40e002404.png)

![](media/376a9249d9d3db785d730fa5b4cb3898.png)

![](media/2fe69ce51eca53bc0639942ca78d5d25.png)

A mentés gomb nem dob munkaablakot, csak felugró ablak formájában értesítést a
sikeres, vagy sikertelen mentések esetén. (Lásd: *Hiba-/Információs üzenet
ablakok* részben a későbbiekben)

![](media/2c3a6e319e4541d0c649c499a763d2a3.png)

![](media/1e94540baf7833959657e0fb39b9d5c7.png)

Adatkezelés:

![](media/d324122b86535346fe51ae97d404add7.png)

![](media/91f2150e1266993290856368b0264662.png)

A törlés gomb nem dob felugró ablakot, csak a kijelölt adatokat törli a
táblamodellből.

![](media/8ba8cce76b6c9723fa7c0a60cdb53218.png)

Minden karakter bevitele, vagy törlése után újra alkalmazza a szűrést a kijelölt

mező alapján.

Hiba- /Információs üzenetek ablakai

![](media/65c1350625028421bf6e6e7409d6dfe6.png)

Adatbázis kapcsolat \> nem .db fájl választása

Megnyitás gomb \> olyan fájl kiválasztása, amely nem .csv, nem .json, vagy nem
.db

![](media/a132cd61b69a4bff6e232d7e616896c1.png)

Új adat felvétele / Adat módosítása / Új fájl létrehozása / Mentés másként \>
Mező üresen hagyása

![](media/3ee37313a8568cfd941a9f57876b70e5.png)

Mentés / Mentés másként \> Sikeres mentés

![](media/520bb35de703e28a130acaae7af78093.png)

Adatbázis kapcsolat \> Sikeres kapcsolat

\+ Kivételek hibaüzenetei

*(Mindegyiket nem idézem elő, csak egy-két példát csatolok. Mindegyik kivétel
saját hibaüzenet ablakkal rendelkezik, ahol a kivétel típusa és üzenete is
megjelenik.)*

![](media/4024acddc1ac8306d6248f18d43094a9.png)

A .json fájl szintaktikája hibás. ( törölt } )

![](media/85f00e944bc826c20ecba99c19f92b97.png)

Egy asd.txt fájl neve lett átírva asd.db-re.

A program 3 külső JAR fájlt használ, ezek a következők:

sqlite-jdbc-3.30.1.jar

jdatepicker-1.3.4.jar

json-simple-1.1.1.jar

Ha ezek valamelyike nem található meg, akkor a program induláskor erre
figyelmeztet:

![](media/79a5c7e0a259be8fedc3b8eca4da7045.png)

Ettől függetlenül a program elindul, azonban, ha olyan funkciót szeretne elérni
a felhasználó, ahol a csomagra szükség van, a funkció nem lesz használható:

![](media/d0655372ea96501898de578982f465e1.png)

Extra funkciók
==============

Keresés – a program el van látva egy kereső mezővel, ami segítségével a táblázat
összes mezője alapján lehet szűrést végezni.

Extra funkciónak tekinthető továbbá az új fájl létrehozása, tetszőleges
fájl(megfelelő adatszerkezet esetén) megnyithatósága és a mentés másként
funkció, ami az éppen megnyitott fájlt írja új, tetszőleges nevű, helyű fájlba.
