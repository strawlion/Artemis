import React from 'react';
import { connect } from 'react-redux'
import { Collapsible } from 'react-materialize';

import PersonRow from './PersonRow';
import api from '../../../utils/api';
import actions from '../../../state/actions';


export default connect(null, mapDispatchToProps)(PersonTable);

function PersonTable({ persons, onPersonFetched }) {

    return (
        <Collapsible>
            {persons.map(person => <PersonRow person={person} key={person.id} onClick={ onRowToggled } />)}
        </Collapsible>
  );

  async function onRowToggled(person) {
      const isAlreadyFetched = person.observations;
      if (isAlreadyFetched) {
          return;
      }

      const newPerson = await api.getPersonById(person.id);
      onPersonFetched(newPerson);
  }
}

function mapDispatchToProps(dispatch) {
  return {
      onPersonFetched,
  };


  function onPersonFetched(person) {
      dispatch(actions.personUpdated(person));
  }
}


