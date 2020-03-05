const api = {
  Login: '/cyauth/login',
  Logout: '/cyauth/logout',
  ForgePassword: '/cyauth/forge-password',
  Register: '/cyauth/register',
  twoStepCode: '/cyauth/2step-code',
  SendSms: '/account/sms',
  SendSmsErr: '/account/sms_err',
  // get my info
  UserInfo: '/cyuser/info',
  // user
  user: '/cyuser/user',
  role: '/cyuser/role',
  permission: '/cyuser/menu',
  dept: '/cyuser/dept',
  dictType: '/cyuser/dict/type',
  dictData: '/cyuser/dict/data',
  dist: '/cyuser/districts',
  config: '/cyuser/config',
  oss: '/cyuser/oss',
  donate: '/cyuser/donate',
  // act
  models: '/cyact/models',
  prof: '/cyact/prof',
  process: '/cyact/process',
  processIns: '/cyact/process/ins',
  business: 'cyact/business',
  leave: 'cyact/leave',
  purchase: 'cyact/purchase',
  node: 'cyact/node',
  task: 'cyact/task'
}
export default api
