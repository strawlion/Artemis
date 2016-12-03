import React from 'react';
import { connect } from 'react-redux'
import PersonTable from './PersonTable';
import PersonFilter from './PersonFilter';


import api from '../../utils/api';
import store from '../../state/store';
import actions from '../../state/actions';

class PersonView extends React.Component {
    constructor({ persons, onPersonsFetched, onFilterChanged }) {
        super();
        api.getPersons()
            .then(onPersonsFetched);
    }

    render() {
        const { persons, onPersonsFetched, onFilterChanged } = this.props;
        return (
            <div style={{ width: '75%' }}>
                <PersonFilter onFilterChanged={onFilterChanged} />
                <PersonTable persons={ persons } />
            </div>
    );
 }
}

export default connect(mapStateToProps, mapDispatchToProps)(PersonView);


function mapStateToProps(state) {
  return {
      persons: state.persons,
  };
}

function mapDispatchToProps(dispatch) {
  return {
      onPersonsFetched,
      onFilterChanged,
  };

  function onPersonsFetched(persons) {
      dispatch(actions.personsChanged(persons));
  }

  function onFilterChanged(category, value) {
      dispatch(actions.filterChanged(category, value));

      api.getPersons(store.getState().filters, store.getState().persons.page)
         .then(onPersonsFetched)
  }
}