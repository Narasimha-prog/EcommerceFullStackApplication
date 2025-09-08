export const environment = {
  production: true,
  kinde: {
    authority: process.env['NEXT_PUBLIC_KINDE_AUTHORITY'] || '',
    redirectUrl: process.env['NEXT_PUBLIC_KINDE_REDIRECT_URL'] || '',
    postLogoutRedirectUri: process.env['NEXT_PUBLIC_KINDE_LOGOUT_URL'] || '',
    clientId: process.env['NEXT_PUBLIC_KINDE_CLIENT_ID'] || '',
    audience: process.env['NEXT_PUBLIC_KINDE_AUDIENCE'] || ''
  },
  apiUrl: process.env['NEXT_PUBLIC_API_URL'] || '',
   razorpayKeyId: 'rzp_test_REfbtgbXooJSTS', // ✅ public key only
};
