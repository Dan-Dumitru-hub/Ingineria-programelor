INSERT INTO utilizatori VALUES
(1941024152511, 'admin', 'Administrescu', NULL, NULL, NULL, 'admin', 'admin', NULL);
INSERT INTO utilizatori VALUES
(1941024152512, 'medic', 'Medicescu', NULL, NULL, NULL, 'medic', 'medic', NULL);
INSERT INTO utilizatori VALUES
(1941024152513, 'pacient', 'Pacientescu', NULL, NULL, NULL, 'pacient', 'pacient', NULL);

INSERT INTO medicatii VALUES
('pizdocalmin', 100);
INSERT INTO medicatii VALUES
('pizdocalmin', 200);

INSERT INTO pacienti_per_medici VALUES
(1941024152512, 1941024152513);

INSERT INTO alimentatii_per_pacienti VALUES
(1941024152513, 'Rabdari prajite toata ziua.');

INSERT INTO medicatii_per_pacienti VALUES
(1941024152513, 'pizdocalmin', 100, '3 pe zi');
INSERT INTO medicatii_per_pacienti VALUES
(1941024152513, 'pizdocalmin', 200, 'cand te apuca pandaliile');
