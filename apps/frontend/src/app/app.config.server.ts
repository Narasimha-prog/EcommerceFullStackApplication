import { ApplicationConfig, Provider, mergeApplicationConfig } from '@angular/core';
import { provideServerRendering, withRoutes } from '@angular/ssr';
import { appConfig } from './app.config';
import { serverRoutes } from './app.routes.server';

const UNIVERSAL_PROVIDERS: Provider[] = [
  { provide: Window, useValue: {} },
  { provide: Document, useValue: {} },
  { provide: Navigator, useValue: { userAgent: 'SSR' } },
  { provide: Location, useValue: {} },
  { provide: console, useValue: console },
  { provide: Performance, useValue: {} },
  { provide: MutationObserver, useValue: class {} }
];

const serverConfig: ApplicationConfig = {
  providers: [
    provideServerRendering(withRoutes(serverRoutes)),
    ...UNIVERSAL_PROVIDERS
  ]
};

export const config = mergeApplicationConfig(appConfig, serverConfig);
