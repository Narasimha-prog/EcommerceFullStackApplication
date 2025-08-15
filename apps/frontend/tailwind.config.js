import { join } from 'path';
import daisyui from 'daisyui';
import { createGlobPatternsForDependencies } from '@nx/angular/tailwind';

export default {
  content: [
    join(__dirname, 'src/**/!(*.stories|*.spec).{ts,html}'),
    ...createGlobPatternsForDependencies(__dirname),
  ],
  theme: {
    fontFamily: {
      sans: 'Inter var, ui-sans-serif, system-ui',
      serif: 'Inter var, ui-sans-serif, system-ui',
    },
    extend: {},
  },
  plugins: [require('@tailwindcss/typography'), daisyui],
  daisyui: {
    themes: [
      {
        fantasy: {
          primary: 'white',
          'primary-content': 'white',
          secondary: '#F6F6F6',
          neutral: '#E8E8E8',
            neutral: '#E8E8E8',
        'base-100': 'pink',
        },
      },
    ],
    base: true,
    styled: true,
    utils: true,
    prefix: '',
    logs: true,
    themeRoot: ':root',
  },
};
