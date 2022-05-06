module.exports = {
  content: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  darkMode: 'class',
  theme: {
    extend: {
      colors: {
        transparent: 'transparent',
        current: 'currentColor',
        'palette1': {
          1: '#F8ECD1',
          2: '#DEB6AB',
          3: '#AC7D88',
          4: '#85586F',
        },
        'palette2': {
          1: '#EFFFFD',
          2: '#B8FFF9',
          3: '#85F4FF',
          4: '#42C2FF',
        },
      },
    },
    letterSpacing: {
      tightest: '-.075em',
      tighter: '-.05em',
      tight: '-.025em',
      normal: '0',
      wide: '.05em',
      wider: '.1em',
      widest: '.2em',
    }
  },
  plugins: [],
}
