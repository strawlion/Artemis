import React from 'react';
import { Collapsible } from 'react-materialize';
import PersonRow from './PersonRow';

export default function PersonTable({ persons }) {

    return (
        <Collapsible>
            {persons.map(person => <PersonRow person={person} key={person.id} />)}
        </Collapsible>
  );
}