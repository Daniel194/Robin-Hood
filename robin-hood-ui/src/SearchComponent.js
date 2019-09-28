import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import magnifier from './images/magnifier.svg';

const useStyles = makeStyles(theme => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: 200,
    },
    dense: {
        marginTop: 19,
    },
    menu: {
        width: 200,
    },
}));

export default function SearchComponent() {
    const classes = useStyles();
    const [values, setValues] = React.useState({
        realName: '',
        posts: '',
        followers: '',
        following: ''
    });

    const handleChange = name => event => {
        setValues({...values, [name]: event.target.value});
    };

    function handleClick(e) {
        let searchString = '';

        if (values.posts !== '') {
            searchString = '?posts=' + values.posts
        }

        if (values.followers !== '') {
            searchString += '&followers=' + values.followers
        }

        if (values.following !== '') {
            searchString += '&following=' + values.following
        }

        if (values.realName !== '') {
            searchString += '&realName=' + values.realName
        }

        if(searchString.charAt(0) === '&') {
            searchString = '?' + searchString.substr(1, searchString.length)
        }

        console.log(searchString)
    }

    return (
        <form className={classes.container} noValidate autoComplete="off">
            <TextField
                id="real-name-search"
                label="Search Real Name"
                value={values.realName}
                onChange={handleChange('realName')}
                type="search"
                className={classes.textField}
                margin="normal"
            />
            <TextField
                id="posts-search"
                label="Posts"
                value={values.posts}
                onChange={handleChange('posts')}
                type="number"
                className={classes.textField}
                margin="normal"
            />
            <TextField
                id="followers-search"
                label="Followers"
                value={values.followers}
                onChange={handleChange('followers')}
                type="number"
                className={classes.textField}
                margin="normal"
            />
            <TextField
                id="following-search"
                label="Following"
                value={values.following}
                onChange={handleChange('following')}
                type="number"
                className={classes.textField}
                margin="normal"
            />
            <img id="magnifier" src={magnifier} onClick={handleClick}/>
        </form>
    );

}