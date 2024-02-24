package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "archivoVersiones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArchivoVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_version", columnDefinition = "BIGINT(20)")
    private Long id;

    @Column(name = "verArchivoOriginal", nullable = false)
    private Long ArchivoOriginal;

    @Column(name = "verArchivoVersion")
    private Long ArchivoVersion;
}
