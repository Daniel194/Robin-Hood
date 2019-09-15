import React from "react";

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
        posts: '',
        followers: '',
        following: ''
    });

    const handleChange = name => event => {
        setValues({ ...values, [name]: event.target.value });
    };

    return (
        <form className={classes.container} noValidate autoComplete="off">
            <TextField
                id="real-name-search"
                label="Search Real Name"
                type="search"
                className={classes.textField}
                margin="normal"
            />
            <TextField
                id="posts-search"
                label="Number"
                value={values.posts}
                onChange={handleChange('posts')}
                type="number"
                className={classes.textField}
                InputLabelProps={{
                    shrink: true,
                }}
                margin="normal"
            />
            <TextField
                id="followers-search"
                label="Number"
                value={values.age}
                onChange={handleChange('followers')}
                type="number"
                className={classes.followers}
                InputLabelProps={{
                    shrink: true,
                }}
                margin="normal"
            />
            <TextField
                id="following-search"
                label="Number"
                value={values.following}
                onChange={handleChange('following')}
                type="number"
                className={classes.textField}
                InputLabelProps={{
                    shrink: true,
                }}
                margin="normal"
            />
        </form>
    );

}