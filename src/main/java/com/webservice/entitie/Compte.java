package com.webservice.entitie;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.TituloEleitoral;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double solde ;
    @Temporal(TemporalType.DATE)
    private Date dateCreation ;
    @Enumerated(EnumType.ORDINAL)
    private TypeCompte typeCompte;

}
