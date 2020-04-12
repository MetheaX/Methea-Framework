var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    },
    methods: {
        clickMe: function () {
            this.message = 'You click me!'
        }
    }
});