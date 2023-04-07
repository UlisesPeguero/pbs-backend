/* Add Modules */
INSERT INTO modules(path, `name`, icon, role)
VALUES ('home', 'Home', 'bi-house', 'employee'),
  (
    'parents',
    'Parents',
    'bi-person-vcard',
    'employee'
  ),
  (
    'kennels',
    'Kennels',
    'bi-grid-3x3-gap-fill',
    'employee'
  ),
  (
    'reservations',
    'Reservations',
    'bi-calendar-plus',
    'employee'
  ),
  (
    'stays',
    'Stays',
    'bi-calendar-check',
    'employee'
  ),
  ('invoices', 'Invoices', 'bi-receipt', 'employee'),
  (
    'employees',
    'Employees',
    'bi-person-rolodex',
    'admin'
  ),
  ('users', 'User', 'bi-people-fill', 'admin');