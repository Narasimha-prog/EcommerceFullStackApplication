import { ApplicationConfig, Provider, mergeApplicationConfig } from '@angular/core';
import { provideServerRendering, withRoutes } from '@angular/ssr';
import { appConfig } from './app.config';
import { serverRoutes } from './app.routes.server';
import {
  WINDOW,
  DOCUMENT,
  NAVIGATOR,
  LOCATION,
  PERFORMANCE,
  MUTATION_OBSERVER
} from './token';

const UNIVERSAL_PROVIDERS: Provider[] = [
  { provide: WINDOW, useValue: {} },
  { provide: DOCUMENT, useValue: {} },
  { provide: NAVIGATOR, useValue: { userAgent: 'SSR' } },
  { provide: LOCATION, useValue: {} },
  { provide: console, useValue: console },
  { provide: PERFORMANCE, useValue: {} },
  { provide: MUTATION_OBSERVER, useValue: class {} }
];

const serverConfig: ApplicationConfig = {
  providers: [
    provideServerRendering(withRoutes(serverRoutes)),
    ...UNIVERSAL_PROVIDERS
  ]
};

export const config = mergeApplicationConfig(appConfig, serverConfig);
