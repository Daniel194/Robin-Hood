import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import './App.css';
import UserComponent from './UserComponent'
import SearchComponent from './SearchComponent'

const useStyles = makeStyles(theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing(3),
        overflowX: 'auto',
    },
    table: {
        minWidth: 650,
    },
}));

export default function App() {
    const classes = useStyles();

    const [state, setState] = React.useState({
        searchString: ""
    });

    function handleData(s) {
        setState({searchString: s});
    }
    
    return (
        <Paper className={classes.root}>
            <SearchComponent handleData={handleData}></SearchComponent>
            <hr/>
            <div class="scrollable">
                <UserComponent className={classes.table} searchString={state.searchString}></UserComponent>
            </div>
        </Paper>
    );
}