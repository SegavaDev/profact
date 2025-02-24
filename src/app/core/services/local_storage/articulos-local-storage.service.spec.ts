import { TestBed } from '@angular/core/testing';

import { ArticulosLocalStorageService } from './articulos-local-storage.service';

describe('ArticulosLocalStorageService', () => {
  let service: ArticulosLocalStorageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ArticulosLocalStorageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
