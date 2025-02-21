import { TestBed } from '@angular/core/testing';

import { ObtenerNitsService } from './obtener-nits.service';

describe('ObtenerNitsService', () => {
  let service: ObtenerNitsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ObtenerNitsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
