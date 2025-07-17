import { createGlobPatternsForDependencies } from '@nx/angular/tailwind';
import { join } from 'path';

/** @type {import('tailwindcss').Config} */
export const content = [
  join(__dirname, 'src/**/!(*.stories|*.spec).{ts,html}'),
  ...createGlobPatternsForDependencies(__dirname),
];
export const theme = {
  fontFamily: {
    sans: 'Inter var, ui-sans-serif, system-ui',
    serif: 'Inter var, ui-sans-serif, system-ui',
  },
  fontSize: {
    sm: '0.875rem',
    base: '1.3rem',
    xl: '1.55rem',
    '2xl': '1.563rem',
    '3xl': '1.953rem',
    '4xl': '2.441rem',
    '5xl': '3.052rem',
  },
  extend: {},
};
export const plugins = [
  require('@tailwindcss/typography'),
  require('daisyui'), // ✅ This is the right place
];
export const daisyui = {
  themes: [
    {
      fantasy: {
        primary: '#0000ff',
        'primary-content': 'white',
        secondary: '#F6F6F6',
        neutral: '#E8E8E8',
      },
    },
  ],
  base: true,
  styled: true,
  utils: true,
  prefix: '',
  logs: true,
  themeRoot: ':root',
};
