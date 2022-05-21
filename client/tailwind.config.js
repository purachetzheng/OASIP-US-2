module.exports = {
  content: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  darkMode: 'class',
  theme: {
    extend: {
      colors: {
        transparent: 'transparent',
        current: 'currentColor',
        'palette1': {
          1: '#E6FAFC',
          2: '#9cfc97',
          3: '#6BA368',
          4: '#515b3a',
          5: '#353D2F',
        },
        'palette2': {
          1: '#F8ECD1',
          2: '#DEB6AB',
          3: '#AC7D88',
          4: '#85586F',
        },
        'palette3': {
          1: '#EFFFFD',
          2: '#B8FFF9',
          3: '#85F4FF',
          4: '#42C2FF',
        },
      },
      spacing: {
        '18': '4.5rem',
        '15': '3.75rem',
        '128': '32rem',
        '160': '40rem',
        '192': '48rem',
        '256': '64rem',
      }
    },
    letterSpacing: {
      tightest: '-.075em',
      tighter: '-.05em',
      tight: '-.025em',
      normal: '0',
      wide: '.05em',
      wider: '.1em',
      widest: '.2em',
    },
    borderWidth: {
      DEFAULT: '1px',
      '0': '0',
      '2': '2px',
      '3': '3px',
      '4': '4px',
      '6': '6px',
      '8': '8px',
    },
  },
  plugins: [],
}
