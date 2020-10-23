import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Apollo } from 'apollo-angular';
import gql from 'graphql-tag';

@Component({
  selector: 'consulta-historial',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  data: any;

  constructor(private apollo: Apollo) { }

  ngOnInit() {

    this.apollo.query({
      query: gql`query {
        historial 
        {
          id,
          ordenId,
          fecha,
          status
        }
      }`
    }).subscribe(({ data, loading }) => {
      this.data = data;
    });


  }
}
