package de.arekkusu;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class Bar
{
    @Inject
    Foo foo;


}