--- Crear db
CREATE DATABASE IF NOT EXISTS prueba_somic;

--- Poblar artículos

INSERT INTO articulos (artCod, artCosto, artLab, artNom, artPrecVenta, artSaldo) VALUES
  ('ART001', 10500, 'LabSalud', 'Aspirina', 15750, 100),
  ('ART002', 20000, 'PharmaPlus', 'Paracetamol', 25500, 200),
  ('ART003', 5000, 'HealthLabs', 'Ibuprofeno', 7500, 300),
  ('ART004', 8000, 'MedTech', 'Vitamina C', 10000, 150),
  ('ART005', 12000, 'LabWell', 'Crema Antiinflamatoria', 18000, 50),
  ('ART006', 30000, 'PharmaMax', 'Jarabe para la Tos', 45000, 75),
  ('ART007', 22500, 'BioPharm', 'Antibiótico', 33000, 60),
  ('ART008', 15000, 'MedLife', 'Inyección Analgésica', 20000, 80),
  ('ART009', 9500, 'LabVital', 'Vitamina D', 12500, 90),
  ('ART010', 14000, 'PharmaCare', 'Antiinflamatorio', 21000, 110),
  ('ART011', 25000, 'HealthCorp', 'Suplemento Mineral', 35000, 95),
  ('ART012', 6000, 'MedLabs', 'Vitamina B12', 8500, 120),
  ('ART013', 11500, 'LabMed', 'Antihistamínico', 16000, 130),
  ('ART014', 16000, 'PharmaHealth', 'Jarabe para el Dolor', 22000, 85),
  ('ART015', 18000, 'WellnessLabs', 'Vitamina E', 24000, 140),
  ('ART016', 7500, 'LabX', 'Inhalador', 9000, 70),
  ('ART017', 10000, 'PharmaPlus', 'Pastillas de Zinc', 13000, 160),
  ('ART018', 28000, 'MedCorp', 'Proteína en Polvo', 40000, 55),
  ('ART019', 13000, 'HealthLabs', 'Multivitamínico', 19000, 180),
  ('ART020', 22000, 'BioHealth', 'Calcio + Vitamina D', 29000, 130),
  ('ART021', 17500, 'PharmaDirect', 'Jarabe para Niños', 23500, 75),
  ('ART022', 19000, 'MedLife', 'Inmunoestimulante', 27000, 65),
  ('ART023', 8000, 'LabWell', 'Antioxidante', 12000, 140),
  ('ART024', 24000, 'PharmaMax', 'Omega 3', 32000, 90),
  ('ART025', 5500, 'HealthCorp', 'Vitamina A', 7500, 200),
  ('ART026', 9000, 'MedTech', 'Vitamina K', 11000, 85),
  ('ART027', 26000, 'LabSalud', 'Suplemento Proteico', 34000, 100),
  ('ART028', 14500, 'PharmaCare', 'Antidepresivo', 20000, 50),
  ('ART029', 31000, 'MedLabs', 'Anticoagulante', 42000, 40),
  ('ART030', 20000, 'BioPharm', 'Analgésico', 28000, 60);

--- Poblar nits

INSERT INTO nits (nitCartera, nitCupo, nitDoc, nitNom, nitPlazo) VALUES
  -- 10 con cupo disponible (cartera = 0)
  (0, 30000, '1234567891', 'Empresa 1', '2024-01-10'),
  (0, 32000, '2345678912', 'Empresa 2', '2024-02-15'),
  (0, 35000, '3456789123', 'Empresa 3', '2024-03-20'),
  (0, 37000, '4567891234', 'Empresa 4', '2024-04-25'),
  (0, 40000, '5678912345', 'Empresa 5', '2024-05-30'),
  (0, 42000, '6789123456', 'Empresa 6', '2024-06-10'),
  (0, 45000, '7891234567', 'Empresa 7', '2024-07-15'),
  (0, 48000, '8912345678', 'Empresa 8', '2024-08-20'),
  (0, 50000, '9123456789', 'Empresa 9', '2024-09-25'),
  (0, 52000, '1023456789', 'Empresa 10', '2024-10-30'),
  (53000, 53000, '1123456789', 'Empresa 11', '2024-11-05'),
  (55000, 55000, '1223456789', 'Empresa 12', '2024-12-10'),
  (57000, 57000, '1323456789', 'Empresa 13', '2025-01-15'),
  (59000, 59000, '1423456789', 'Empresa 14', '2025-02-20'),
  (60000, 60000, '1523456789', 'Empresa 15', '2025-03-25'),
  (62000, 62000, '1623456789', 'Empresa 16', '2025-04-30'),
  (64000, 64000, '1723456789', 'Empresa 17', '2025-05-05'),
  (66000, 66000, '1823456789', 'Empresa 18', '2025-06-10'),
  (68000, 68000, '1923456789', 'Empresa 19', '2025-07-15'),
  (70000, 70000, '2023456789', 'Empresa 20', '2025-08-20'),
  (20000, 75000, '2123456789', 'Empresa 21', '2025-09-25'),
  (30000, 80000, '2223456789', 'Empresa 22', '2025-10-30'),
  (40000, 85000, '2323456789', 'Empresa 23', '2025-11-15'),
  (45000, 90000, '2423456789', 'Empresa 24', '2025-12-20'),
  (50000, 95000, '2523456789', 'Empresa 25', '2024-11-15'),
  (55000, 97000, '2623456789', 'Empresa 26', '2024-12-20'),
  (60000, 99000, '2723456789', 'Empresa 27', '2025-01-25'),
  (65000, 100000, '2823456789', 'Empresa 28', '2025-03-05'),
  (70000, 85000, '2923456789', 'Empresa 29', '2025-04-10'),
  (75000, 80000, '3023456789', 'Empresa 30', '2025-05-15');

