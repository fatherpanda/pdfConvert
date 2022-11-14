import { createRouter, createWebHistory } from "vue-router";

const routes = [
    {
        name: "Main",
        component: () => import("/@views/MainWarp.vue"),
        children: [
            {
                path: '/',
                name: 'INDEX',
                component: () => import("/@views/Main.vue"),
                props:true,
            },
            {
                path: '/aa01',
                name: 'AA01',
                component: () => import("/@views/Aa01.vue"),
                props:true,
            },
            {
                path: '/aa02',
                name: 'AA02',
                component: () => import("/@views/Aa02.vue"),
                props:true,
            },
            {
                path: '/aa04',
                name: 'AA04',
                component: () => import("/@views/Aa04.vue"),
                props:true,
            },
            {
                path: '/ba02',
                name: 'BA02',
                component: () => import("/@views/Ba02.vue"),
                props:true,
            },
            {
                path: '/ba04',
                name: 'BA04',
                component: () => import("/@views/Ba04.vue"),
                props:true,
            },
        ]

        //     {
        //         path: 'accountunverified',
        //         name: 'ACCOUNTUNVERIFIED',
        //         component: () => import("/@views/AccountConfirmNeed.vue"),
        //     },
        //     {
        //         path: 'accountverified',
        //         name: 'ACCOUNTVERIFIED',
        //         component: () => import("/@views/AccountActivate.vue"),
        //     },
        //     {
        //         path: '/changepw',
        //         name: 'CHANGEPW',
        //         meta: { authorized: "any" },
        //         component: () => import("/@views/NewPassword.vue"),
        //         props: true,
        //
        //     },
        //     {
        //         path: '/newpwemail',
        //         name: 'NEWPWEMAIl',
        //         component: () => import("/@views/NewPassword4.vue"),
        //     },
        //     {
        //         path: '/newpwinput',
        //         name: 'NEWPWINPUT',
        //         meta: { authorized: "any" },
        //         component: () => import("/@views/NewPassword2.vue"),
        //         props: true,
        //     },
        //     {
        //         path: '/changepwcomplete',
        //         name: 'CHANGEPWCOMPLETE',
        //         meta: { authorized: "any" },
        //         component: () => import("/@views/NewPassword3.vue"),
        //     },
        //     {
        //         path: '/user',
        //         name: 'USER_INFORMATION',
        //         component: () => import("/@views/UserInfo.vue"),
        //         meta: { authorized: "login" },
        //         redirect: '/user/account',
        //         children: [
        //             {
        //                 path: 'account',
        //                 name: 'USER_INFORMATION_ACCOUNT',
        //                 component: () => import("/@views/UserAccount.vue"),
        //             },
        //             {
        //                 path: 'wallet',
        //                 name: 'USER_INFORMATION_WALLET',
        //                 component: () => import("/@views/UserWallet.vue"),
        //             },
        //             {
        //                 path: 'world',
        //                 name: 'USER_INFORMATION_WORLD',
        //                 props: true,
        //                 component: () => import("/@views/UserWorld.vue"),
        //             },
        //             {
        //                 path: 'land',
        //                 name: 'USER_INFORMATION_LAND',
        //                 props: true,
        //                 component: () => import("/@views/UserLand.vue"),
        //             },
        //             {
        //                 path: 'people',
        //                 name: 'USER_INFORMATION_PEOPLE',
        //                 redirect: 'people/myincomes',
        //                 component: () => import("/@views/UserPeopleWrap.vue"),
        //                 children: [
        //                     {
        //                         path: 'myincomes',
        //                         name: 'USER_INFORMATION_PEOPLE_MYINCOMES',
        //                         component: () => import("/@views/UserPeopleIncome.vue"),
        //                     },
        //                     {
        //                         path: 'history',
        //                         name: 'USER_INFORMATION_PEOPLE_HISTORY',
        //                         component: () => import("/@views/UserPeopleHistory.vue"),
        //                     },
        //                     {
        //                         path: 'my',
        //                         name: 'USER_INFORMATION_PEOPLE_MY_PEOPLE',
        //                         component: () => import("/@views/UserPeopleMy.vue"),
        //                     }
        //                 ]
        //             }
        //         ]
        //     },
        //     {
        //         path: '/WorldSale',
        //         name: 'WORLD_SALE',
        //         meta: { authorized: "login" },
        //         component: () => import("/@views/SaleWorld.vue"),
        //     },
        //     {
        //         path: '/LandSale',
        //         name: 'LAND_SALE',
        //         meta: { authorized: "login" },
        //         component: () => import("/@views/SaleLand.vue"),
        //     },
        //     {
        //         path: '/login',
        //         name: 'LOGIN',
        //         component: () => import("/@views/Login.vue"),
        //         props:true,
        //     },
        //     {
        //         path: '/signup',
        //         name: 'SignUp',
        //         props: true,
        //         component: () => import("/@views/Signup.vue"),
        //     },
        //     {
        //         path: '/joincomplete',
        //         name: 'JOINCOMPLETE',
        //         component: () => import("/@views/AccountCongratulations.vue"),
        //     },
        //     {
        //         path: '/test2',
        //         name: 'TEST',
        //         component: () => import("/@views/Test.vue"),
        //     },
        //
        // ]
    },
    

];

const router = createRouter({
    history: createWebHistory(),
    routes,
    scrollBehavior (to, from, savedPosition) {
        // always scroll to top
        return { top: 0 }
    },
});


// login guard
 router.beforeEach((to, from, next) => {

    // const loginStore = LoginStore()
    // const token=loginStore.getToken
    // const isAuthorized=to.matched.some(record => record.meta.authorized)
    // const authorized=to.meta.authorized
    // if (isAuthorized && ((authorized=="login" && (token!=="" && token!==null)) || authorized=="any")) {
    //     // next();
    // } else if(isAuthorized==false) {
    //     if(token!=="" && token!==null && to.path!='/') {
    //         router.push('/');
    //     }
    // } else if(token==null || token=="") {
    //     router.push('/login');
    // }

    next();

/*

alert('로그인 해주세요');
return next('/login');
if (to.path == "/") {

    const result = sessionCheck()
    if (token === "") {
        next('/login');
    } else {
        next();
    }
    // result.then((response) => {
    //     if (response.nvm_id === "") {
    //         next('/login');
    //     } else {
    //         next();
    //     }
    //         console.log(response)
    //         // if (!isLoggedIn) {
    //         //     next(false);
    //         //     auth.login();
    //         // } else {
    //         // }
    // })
} else {
    next();
}
*/
});

export default router;