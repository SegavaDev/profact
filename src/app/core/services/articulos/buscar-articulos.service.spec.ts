import { TestBed } from '@angular/core/testing';

import { BuscarArticulosService } from './buscar-articulos.service';

describe('BuscarArticulosService', () => {
  let service: BuscarArticulosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BuscarArticulosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
