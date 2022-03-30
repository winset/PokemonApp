package com.space.myapplication.data.pokemons

import com.space.myapplication.core.Abstract

interface PokemonsDataToDomainMapper<T> : Abstract.Mapper.DataToDomain<List<PokemonData>,T>