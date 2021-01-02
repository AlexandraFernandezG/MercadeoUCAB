import { TestBed } from '@angular/core/testing';

import { PreguntasestudiosService } from './preguntasestudios.service';

describe('PreguntasestudiosService', () => {
  let service: PreguntasestudiosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PreguntasestudiosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
