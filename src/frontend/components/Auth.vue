<template lang='pug'>
    form(action='')
        .modal-card(style='width: auto')
            header.modal-card-head
                p.modal-card-title Login
            section.modal-card-body
                b-field(label='Email')
                    b-input(type='email', :value='email', placeholder='Your email', required='')
                b-field(label='Password')
                    b-input(type='password', :value='password', password-reveal='', placeholder='Your password', required='')
                b-checkbox Remember me
            footer.modal-card-foot
                button.button(type='button', @click='$parent.close()') Close
                button.button.is-primary Log in!
</template>

<script lang="ts">
    export default {
        name: 'Auth',
        props: ['email', 'password'],
        data() {
            return {
                formProps: {
                    email: 'evan@you.com',
                    password: 'testing',
                }
            }
        }
    }
</script>
