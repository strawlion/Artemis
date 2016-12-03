import React from 'react';
import { connect } from 'react-redux'
import { Collapsible, Preloader } from 'react-materialize';

import PersonRow from './PersonRow';
import api from '../../../utils/api';
import actions from '../../../state/actions';


export default connect(null, mapDispatchToProps)(PersonTable);

function PersonTable({ persons, onPersonFetched }) {

    const loadingTemplate = persons.isLoading ? <Preloader size="small" /> : <span></span>;
    return (
        <Collapsible>
            { persons.value.map(person => <PersonRow person={person} key={person.id} onClick={ onRowToggled } />) }
            { loadingTemplate }
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


