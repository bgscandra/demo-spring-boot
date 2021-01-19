package com.example.demo.audit;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class AuditMarker {
    protected static Marker getMaker() {
        return MarkerFactory.getMarker("AuditRecord");
    }
}
