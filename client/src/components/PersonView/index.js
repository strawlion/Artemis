import React from 'react';
import { connect } from 'react-redux'
import PersonTable from './PersonTable';
import PersonFilter from './PersonFilter';

import api from '../../utils/api';
import actions from '../../state/actions';

class PersonView extends React.Component {
    constructor({ persons, onPersonsFetched }) {
        super();
        api.getPersons()
            .then(onPersonsFetched);
    }

    render() {
        const { persons, onPersonsFetched } = this.props;
        return (
            <div style={{ width: '75%' }}>
                <PersonFilter />
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
  };

  function onPersonsFetched(persons) {
      dispatch(actions.personsChanged(persons));
  }
}