package com.gotunis.gestionreservation.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long idActivity;

    private long idMaison;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date date;

    private int count;

    private boolean done;

    private boolean cancel;

    private boolean validate;

    private String reasonCancel;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "reservationActivity",
            cascade = CascadeType.ALL)
    private List<ReservationManagement> reservationManagementList  ;
}
