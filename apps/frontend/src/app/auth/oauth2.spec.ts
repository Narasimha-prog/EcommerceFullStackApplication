import { TestBed } from '@angular/core/testing';

import { Oauth2Service } from './oauth2';

describe('Oauth2', () => {
  let service: Oauth2Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Oauth2Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
