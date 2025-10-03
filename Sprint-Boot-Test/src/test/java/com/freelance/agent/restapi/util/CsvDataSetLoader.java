package com.freelance.agent.restapi.util;

import java.io.IOException;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

public final class CsvDataSetLoader extends AbstractDataSetLoader {

    @Override
    protected IDataSet createDataSet(final Resource resource)
            throws IOException, DataSetException {
        return new CsvDataSet(resource.getFile());
    }
}
