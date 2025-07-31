import { TestBed } from '@angular/core/testing';

import { Oauth2 } from './oauth2';

describe('Oauth2', () => {
  let service: Oauth2;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Oauth2);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
